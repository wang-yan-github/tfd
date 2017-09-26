function printer(flag)
{
    var brapp="";
    var version="";
    if(browser.ie)
    {
        brapp="ie";
        version=browser.version;
    }
    if(browser.firefox)
    {
        brapp="firefox";
        version=browser.firefox;
    }else if(browser.chrome)
    {
        brapp="chrome";
        version=browser.version;
    }else if(browser.safari)
    {
        brapp="safari";
        version=browser.safari;
    }
    if(brapp=="ie")
    {
        if(flag==1)
        { //打印设置  
        printWB.ExecWB(8,1); 
        }else if(flag==2)
        { //打印预览
        printWB.ExecWB(7,1);
        }else if(flag==3)
        {//直接打印  
         printWB.ExecWB(6,6);
        }else if(flag==4)
        {//关闭窗口
            printWB.ExecWB(45,1);
        }        
    }else if(brapp=="firefox")
        {
            if(flag==3)
            {
           window.print();
            }else if(flag==4)
            {
                window.close();
            }
        }else if(brapp=="chrome")
        {
            if(flag==3)
            {
           window.print();
            }else if(flag==4)
            {
                window.close();
            }
            }else if(brapp=="safari")
            {
                if(flag==3)
            {
               window.print();
            }else if(flag==4)
            {
                window.close();
            }
            }else {
        layer.msg("不支持当前浏览器！");
    }
}

function getBrowerinfo()
{
    var binfo="";
    if(browser.chrome)
    {
        binfo="chrome";
    }else if (browser.firefox)
    {
        binfo="firefox";   
    }else if(browser.ie)
    {
        binfo="ie";
    }else if(browser.safari)
    {
        binfo="safari";
    }
    return binfo;
}


 var browser = function () {
    var agent = navigator.userAgent.toLowerCase(), 
    opera = window.opera, 
    browser = { 
        //检测当前浏览器是否为IE 
        ie: /(msie\s|trident.*rv:)([\w.]+)/.test(agent), 
        //检测当前浏览器是否为Opera 
        opera: (!!opera && opera.version), 
        //检测当前浏览器是否是webkit内核的浏览器 
        webkit: (agent.indexOf(' applewebkit/') > -1), 
        //检测当前浏览器是否是firefox内核
        firefox:(agent.indexOf(' firefox/')> -1),
        //检测当前浏览器是否是运行在mac平台下 
        mac: (agent.indexOf('macintosh') > -1), 
        //检测当前浏览器是否处于“怪异模式”下 
        quirks: (document.compatMode == 'BackCompat') 
    }; 
    //检测当前浏览器内核是否是gecko内核 
    browser.gecko = (navigator.product == 'Gecko' && !browser.webkit && !browser.opera && !browser.ie); 
    var version = 0; 
    // Internet Explorer 6.0+ 
    if (browser.ie) { 
        var v1 = agent.match(/(?:msie\s([\w.]+))/); 
        var v2 = agent.match(/(?:trident.*rv:([\w.]+))/); 
        if (v1 && v2 && v1[1] && v2[1]) { 
            version = Math.max(v1[1] * 1, v2[1] * 1); 
        } else if (v1 && v1[1]) { 
            version = v1[1] * 1; 
        } else if (v2 && v2[1]) { 
            version = v2[1] * 1; 
        } else { 
            version = 0; 
        }
        //检测浏览器模式是否为 IE11 兼容模式 
        browser.ie11Compat = document.documentMode == 11; 
        //检测浏览器模式是否为 IE9 兼容模式 
        browser.ie9Compat = document.documentMode == 9; 
        //检测浏览器模式是否为 IE10 兼容模式 
        browser.ie10Compat = document.documentMode == 10; 
        //检测浏览器是否是IE8浏览器 
        browser.ie8 = !!document.documentMode; 
        //检测浏览器模式是否为 IE8 兼容模式 
        browser.ie8Compat = document.documentMode == 8; 
        //检测浏览器模式是否为 IE7 兼容模式 
        browser.ie7Compat = ((version == 7 && !document.documentMode) || document.documentMode == 7); 
        //检测浏览器模式是否为 IE6 模式 或者怪异模式 
        browser.ie6Compat = (version < 7 || browser.quirks); 
        browser.ie9above = version > 8; 
        browser.ie9below = version < 9; 
    } 

    // Gecko. 
    if (browser.gecko) { 
        var geckoRelease = agent.match(/rv:([\d\.]+)/); 
        if (geckoRelease) { 
            geckoRelease = geckoRelease[1].split('.'); 
            version = geckoRelease[0] * 10000 + (geckoRelease[1] || 0) * 100 + (geckoRelease[2] || 0) * 1; 
        } 
    } 
    //检测当前浏览器是否为Chrome, 如果是，则返回Chrome的大版本号 
    if (/chrome\/(\d+\.\d)/i.test(agent)) { 
        browser.chrome = +RegExp['\x241']; 
    } 
     //检测当前浏览器是否为Firefox, 如果是，则返回Chrome的大版本号 
    if (/firefox\/(\d+\.\d)/i.test(agent)) { 
        browser.firefox = +RegExp['\x241']; 
    } 
    //检测当前浏览器是否为Safari, 如果是，则返回Safari的大版本号 
    if (/(\d+\.\d)?(?:\.\d)?\s+safari\/?(\d+\.\d+)?/i.test(agent) && !/chrome/i.test(agent)) { 
        browser.safari = +(RegExp['\x241'] || RegExp['\x242']); 
    } 
    // Opera 9.50+ 
    if (browser.opera) 
        version = parseFloat(opera.version()); 
    // WebKit 522+ (Safari 3+) 
    if (browser.webkit) 
        version = parseFloat(agent.match(/ applewebkit\/(\d+)/)[1]); 
    //检测当前浏览器版本号 
    browser.version = version; 
    return browser; 
}();