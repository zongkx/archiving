#include"res.h"

//�˿���Ϣ¼�� 
void enter()
{
	system("cls");
	user *q;
	q=new user;
	q->next=NULL;
PRN:	cout<<endl<<"������˿͵�������";
	cin>>q->uname;
	cout<<"������˿͵�id��";
	cin>>q->uid;
	cout<<"������˿͵��Ա�";
	cin>>q->sex;
	
	
/*����ʱ�����ɺ���*/ 
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
    strcpy(q->time,yearr);  
    
    FILE *fp=fopen("user.txt","a");
    fprintf(fp,"%s %ld %c %s\n",q->uname,q->uid,q->sex,q->time);
	fclose(fp); 
    
    cout<<"�������� 1  �����ϼ��˵� 0"<<endl;
	int d;
	cin>>d;
	cout<<endl;
	if(d==1)
	  {goto PRN;} 
}
	 
	
	

