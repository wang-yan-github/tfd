function createmustfield(data)
{
    var html1="";
    var html2="";
    for(var i=0;data.length>i;i++)
    {
        html1+="  <input type=\"checkbox\" name=\"mustfield\"  value=\""+data[i].field+"\"";
        html2+="  <input type=\"checkbox\" name=\"hiddenfield\"  value=\""+data[i].field+"\"";
        if(data[i].mustfield=="1")
        {
            html1+=" checked ";
        }
        if(data[i].hiddenfield=="1")
        {
            html2+=" checked ";
        }
        html1+="/>"+data[i].title;
        html2+="/>"+data[i].title;
    }
    $("#createmustfield").html(html1);
    $("#createhiddenfield").html(html2);
}
