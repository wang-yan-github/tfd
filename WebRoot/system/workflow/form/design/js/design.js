var fieldList=null;
var form=null;

$(function(){
	$("#panel").floatToolsPanel();
	
	CKEDITOR.editorConfig = function( config ){
		var html = "<div style=\"valign:middle;width:200px;height:100%;background-color:#0093FF;line-height:60px;text-align:center;margin-top:60px;color:#FFF;font-weight:900;\" align=\"center\" >请选择上传图片</div>";
		config.image_previewText=html; //预览区域显示内容
		config.filebrowserImageUploadUrl= contextPath+"/com/system/filetool/UpImgTool/imgUpload.act"; //待会要上传的action或servlet
		config.extraPlugins = 'xinput,xtextarea,xtextuedit,xradio,xselect,xsql,xiframe,xcheckbox,xcalculate,xfetch,xlist,xseal,xmacrotag,xmacro,ximg,xdocnum,xworkflow,xuploads,xworkflow,xupload';
		config.menu_groups = "clipboard,form,tablecell,tablecellproperties,tablerow,tablecolumn,table,anchor,link,flash,hiddenfield,imagebutton,button,div,xinput,xtextarea,xtextuedit,xsql,xiframe,xradio,xselect,xcheckbox,xcalculate,xfetch,xlist,xseal,xmacrotag,xmacro,ximg,xdocnum,xuploads,xworkflow,xupload";
		config.plugins = 'about,a11yhelp,basicstyles,bidi,blockquote,clipboard,colorbutton,colordialog,contextmenu,div,elementspath,enterkey,entities,filebrowser,find,flash,floatingspace,font,format,forms,horizontalrule,htmlwriter,image,iframe,indent,justify,link,list,liststyle,magicline,maximize,newpage,pagebreak,pastefromword,pastetext,preview,print,removeformat,resize,save,scayt,selectall,showblocks,showborders,smiley,sourcearea,specialchar,stylescombo,tab,table,tabletools,templates,toolbar,undo,wsc,wysiwygarea';
		config.allowedContent = true;
		config.baseFloatZIndex = 100;
		config.removePlugins='forms';
		config.toolbar = 'MyToolbar';//把默认工具栏改为‘MyToolbar’   		  
		config.toolbar_MyToolbar =   
			[   
			 ['Source','NewPage','Preview','Templates'],
			 ['Cut','Copy','Paste','PasteText','PasteFromWord','SpellChecker','Scayt'],   
			 ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'], 
			 ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],   
			 ['Styles','Format','Color','Font','FontSize'],   
			 ['Bold','Italic','Strike'],   
			 ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'], 
			 ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			 ['Link','Unlink','Anchor'],
			 ['TextColor','BGColor'],
			 [ 'AlignLeft', 'AlignCenter', 'AlignRight', 'AlignJustify' ],
			 ['Maximize', 'ShowBlocks','-','About']
			 ];   
	};
	CKEDITOR.replace('editor1',{
		width : 'auto',
		height:'600px'
	});
	CKEDITOR.on('instanceReady', function (e) {
		editor = e.editor;
		getFlowForm();
		if(form!=""&&form!=null&&form!="null"){
			editor.setData(form);
		}else{
			editor.setData("");
		}
	});
	getFieldList();
});

function getFieldList(){
   var requestUrl = contextPath+"/tfd/system/workflow/flowformitem/act/FlowFormItemAct/getValidateByFormIdAct.act";
   $.ajax({
        url:requestUrl,
        dataType:"json",
        data:{formId:formId},
        async:false,
        success:function(data){
        	fieldList=data;
        }
    });
}
//获取表单实体
function getFlowForm(){
    var requestUrl = contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/getFormCodeByFormIdAct.act";
    $.ajax({
        url:requestUrl,
        dataType:"text",
        data:{formId:formId},
        async:false,
        success:function(data){
                    form=data;
        }
    });
};

//检查重名控件
function checkNameRepeated(title){
    var maps = getNamesMap();
    if(maps[title]){
        return false;
    }
    return true;
}

function getNamesMap(){
    var dom = $(editor.getData());
    var imgs = dom.find("img[title]");
    var inputs = dom.find("input[title]");
    var selects = dom.find("select[title]");
    var textareas = dom.find("textarea[title]");

    var maps = {};
    imgs.each(function(i,obj){
        var key = $(obj).attr("title");
        if(!maps[key]){
            maps[key]=1;
        }
    });
    inputs.each(function(i,obj){
        var key = $(obj).attr("title");
        if(!maps[key]){
            maps[key]=1;
        }
    });
    selects.each(function(i,obj){
        var key = $(obj).attr("title");
        if(!maps[key]){
            maps[key]=1;
        }
    });
    textareas.each(function(i,obj){
        var key = $(obj).attr("title");
        if(!maps[key]){
            maps[key]=1;
        }
    });
    return maps;
}

function previewForm(){
    window.open(contextPath+"/system/workflow/dowork/"+leavePath+"/preview/index.jsp");
}
    
function commit(){
    var editor = CKEDITOR.instances.editor1;
    var content=editor.getData();
	$.ajax({
	    url:contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/greateFormTableAct.act",
		type:"POST",
		async:false,
		data:{htmlCode:content,formId:formId},
		dataType:"text",
		success:function(data){
			if (data=="true") {
				layer.msg("保存成功！");
			}else{
				layer.msg("操作失败！！！");
			}
	    }
	});
}

function cssEdit(){
    var url = contextPath+"/system/workflow/form/design/style.jsp?formId="+formId+"&rand="+new Date().getTime();
    $("#modaliframe").attr("src",url);
    $("#myModalLabel").html("样式编辑器");
    $("#div-modal-dialog").width(360);
    $("#modaliframe").width(355);
    $("#modaliframe").height(220);
    $('#modal').modal({backdrop: 'static', keyboard: false});
    $('#modal').on('shown.bs.modal',function(){
    $("#savedata").unbind('click').click(function (){
    window["modaliframe"].commit();
    $('#modal').modal("hide");
        }
        );
    });
}

function scriptEdit(){
    var url = contextPath+"/system/workflow/form/design/script.jsp?formId="+formId+"&rand="+new Date().getTime();
    $("#modaliframe").attr("src",url);
    $("#myModalLabel").html("动态脚本");
    $("#div-modal-dialog").width(360);
    $("#modaliframe").width(355);
    $("#modaliframe").height(220);
    $('#modal').modal({backdrop: 'static', keyboard: false});
    $('#modal').on('shown.bs.modal',function(){
    	$("#savedata").unbind('click').click(function (){
    		window["modaliframe"].commit();
    		$('#modal').modal("hide");
        });
    });
}
    
function improtform(){
    $('#improtform').modal('show');
}

$.fn.floatToolsPanel=function(){
    var cur = $(this);
    var resize = function(){
        var wheight = $(window).height();
        var wwidth = $(window).width();
        cur.css({height:wheight,top:0,left:wwidth-parseInt(cur.css("width"))});
    };
    $(window).resize(function(){
        resize();
    });
    var wheight = $(window).height();
    var wwidth = $(document).width();
    cur.css({height:wheight,top:0,left:wwidth-parseInt(cur.css("width"))});

    var hidePixes = 120;

    cur.attr("owidth",parseInt(cur.css("width")));
    var wheight = $(window).height();
    var wwidth = $(document).width();
    var oWidth = cur.attr("owidth");

    cur.mouseover(function(){
        var wheight = $(window).height();
        var wwidth = $(document).width();
        var oWidth = cur.attr("owidth");
        cur.stop();
    });

    cur.mouseout(function(){
        var wheight = $(window).height();
        var wwidth = $(document).width();
        var oWidth = cur.attr("owidth");
        cur.stop();
    });
};