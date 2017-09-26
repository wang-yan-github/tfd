package tfd.system.regist.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import tfd.system.regist.data.Reg;
import tfd.system.unit.account.logic.AccountLogic;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.global.DateConst;
import com.system.global.SysProps;
import com.system.tool.SysTool;

public class RegistLogic {
    public BaseDao dao = new BaseDaoImpl();

    /**
     * 对注册文件内容解密
     * 
     * @param regFileContent
     * @return
     * @throws Exception
     */
    public String decodeRegFile(byte[] regBytes) throws Exception {
        if (regBytes == null) {
            return null;
        }
        String[] $ = new String[] { "###", "|||", "!!!", "~~~", "@@@", "$$$", "%%%", "^^^", "***", "(((",
                ")))", "+++", "===", "&&&" };
        String patternStr = "";
        for (int i = 0; i < $.length; i++) {
            String a$s = $[i].substring(0, 1);
            patternStr += "(\\" + a$s + "{3}[0|1]+\\" + a$s + "{3})|";
        }
        patternStr = patternStr.substring(0, patternStr.length() - 1);

        List<String> encodeIndexStrs = new ArrayList<String>();
        Matcher m = Pattern.compile(patternStr).matcher(new String(regBytes, "utf-8"));
        while (m.find()) {
            encodeIndexStrs.add(m.group());
        }

        int k = 0;
        List<Byte> regBodyBytes = new ArrayList<Byte>();
        for (int i = 0; i < regBytes.length; i++) {
            regBodyBytes.add(regBytes[i]);
            if (k < encodeIndexStrs.size()) {
                int l = encodeIndexStrs.get(k).getBytes("utf-8").length;
                i += l;
                k++;
            }
        }

        List<Integer> encodeIndexs = new ArrayList<Integer>();
        for (int i = 0; i < encodeIndexStrs.size(); i++) {
            String $$ = encodeIndexStrs.get(i).substring(0, 3);
            int random = 0;
            for (int j = 0; j < $.length; j++) {
                if ($[j].equals($$)) {
                    random = j;
                }
            }
            int index = Integer.valueOf(
                    encodeIndexStrs.get(i).substring(3, encodeIndexStrs.get(i).length() - 3), 2);
            index = index - random;
            encodeIndexs.add(index);
        }

        byte[] decodeRegBytes = new byte[regBodyBytes.size()];
        for (int j = 0; j < regBodyBytes.size(); j++) {
            if (encodeIndexs.contains(j)) {
                decodeRegBytes[j] = (byte) (regBodyBytes.get(j) - 2);
            } else {
                decodeRegBytes[j] = regBodyBytes.get(j);
            }
        }

        return new String(decodeRegBytes, "utf-8");
    }

    /**
     * 
     * @param reg
     * @param conn
     * @return
     * @throws Exception
     */
    public boolean validate(Reg reg, Connection conn) throws Exception {
        // //1.硬盘序列号
        // //2.时间
        // //3.用户数
        // //4.im 用户数
        if (reg == null) {
            return false;
        }

        String disk = this.getClass().getClassLoader().getResource(File.separator).getPath().substring(1, 2);

        String diskSn = getHardDiskSN(disk);
        if (!diskSn.equals(reg.getDiskSn())) {
            return false;
        }

        if (reg.getDeadline() != null) {
            if (reg.getDeadline().getTime() - reg.getRegTime().getTime() < 0) {
                return false;
            }
        }

        Integer userCount = new AccountLogic().getCountOfAllAccount(conn);
        if (userCount == null || userCount > reg.getUserCount()) {
            return false;
        }

        return true;
    }

    public boolean validate(Connection conn) throws Exception {
        try {
            return validate(decodedRegFileToObject(decodeRegFile(readRegFile())), conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 读取注册文件内容
     * 
     * @param regFileInputStream
     * @return
     * @throws Exception
     */
    public byte[] readRegFile(InputStream regFileInputStream) throws Exception {
        List<Byte> regBytes = new ArrayList<Byte>();

        int b = -1;
        while ((b = regFileInputStream.read()) > -1) {
            regBytes.add((byte) b);
        }

        byte[] regBytesNew = new byte[regBytes.size()];
        for (int i = 0; i < regBytesNew.length; i++) {
            regBytesNew[i] = regBytes.get(i);
        }
        return regBytesNew;
    }

    /**
     * 读取注册文件内容
     * 
     * @param regFileInputStream
     * @return
     * @throws Exception
     */
    public byte[] readRegFile() throws Exception {
        byte[] regBytes = null;
        InputStream regFileInputStream = null;
        try {

            File regFile = new File(SysProps.getWebInfoPath() + "/config/reg.lsq");
            if (!regFile.exists()) {
                return null;
            }

            regBytes = new byte[(int) regFile.length()];

            regFileInputStream = new FileInputStream(regFile);

            int b = -1;
            int i = 0;
            while ((b = regFileInputStream.read()) > -1) {
                regBytes[i] = (byte) b;
                i++;
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            if (regFileInputStream != null) {
                regFileInputStream.close();
            }
        }
        return regBytes;
    }

    public Reg decodedRegFileToObject(String regFileContent) throws Exception {

        if (regFileContent == null) {
            return null;
        }
        JSONObject reg = JSONObject.fromObject(regFileContent);
        return new Reg(reg.getString("unit"), reg.getString("version"), reg.getString("sn"),
                SysTool.parseDate(DateConst.VALUE_SHORT_DATE, reg.getString("regTime")), !reg.getString(
                        "deadline").equals("") ? SysTool.parseDate(DateConst.VALUE_SHORT_DATE,
                        reg.getString("deadline")) : null, Integer.parseInt(reg.getString("userCount")),
                Integer.parseInt(reg.getString("imUserCount")), reg.getString("diskSn"),
                reg.getString("productName"), reg.getString("productTeam"), reg.getString("productSite"));
    }

    /**
     * 获取硬盘序列号
     * 
     * @param drive
     *            盘符
     * @return
     */
    public String getHardDiskSN(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive
                    + "\")\n" + "Wscript.Echo objDrive.SerialNumber"; // see
            // note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
        // return "123456789";
    }
}
