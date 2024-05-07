#include"supermarket.h"

char time(char d[]) 
{
	time_t ctime;
    struct tm *timeinfo;
    time(&ctime);
    timeinfo=localtime(&ctime);
    int a,b,c;
    char yearr[10],monn[3],dayy[3];
    a=timeinfo->tm_year+1900;
    b=timeinfo->tm_mon+1;
    c=timeinfo->tm_mday;
    itoa(a, yearr, 10);
    itoa(b, monn, 10);
    itoa(c, dayy, 10);
    strcat(yearr,"-");
    strcat(yearr,monn);
	strcat(yearr,"-");
	strcat(yearr,dayy);
    strcpy(d,yearr); 
}


