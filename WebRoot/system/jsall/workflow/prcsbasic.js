function createAllNextPrcs(allPrcs)
{
	var html="";
	for(var i in allPrcs){
	html=html+"<div class=\"col-xs-8\"><input type=\"checkbox\" id=\"id_"+allPrcs[i].prcsId+"\" value=\""+allPrcs[i].prcsId+"\" name=\"allPrcs\">"+allPrcs[i].prcsName+"</div>";
	}
	return html;
}