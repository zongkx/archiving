#include"bank.h"
using namespace std;

void create(){
    system("cls");
	int i;
	point *q,*v;
	q=new point;
	FILE *fp;
	fp=fopen("saveuser.txt","a+");
	cout<<"------------------��������������------------------"<<endl;
	cin>>q->name ;
	cout<<"----------------������������˺�Ϊ----------------"<<endl;
	srand((unsigned)time(NULL));int m=rand();q->ac =m+100000000000;
	printf("====%d=====\n",q->ac);
    strcpy(q->password,"000000" );
	cout<<endl;
	cout<<"������������루������������ĸ������룩\n Ĭ��Ϊ000000���޸��밴1,�����밴0�� " <<endl;int x=0, j;cin>>j;
	  while(j>0)
	   {
	        while(1)
			{
               q->password[x]=getch();
               if(q->password [x]!='\r')
			       {printf("*"); ++x;}
               else break;
            }
            q->password [x]='\0';
            printf("\n");break;
		}

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
    strcpy(q->ctime,yearr);
	
	cout<<"------------------�����������Ľ��------------------"<<endl;
    cin>>q->money ;
	cout<<"------------------�������������֤��------------------"<<endl;
    cin>>q->id ;
    q->mark =1;
    q->mark1=1; 
    q->next=NULL;
	strcpy(q->dtime,"0000-00-00");
    fprintf(fp,"%s %s %s %s %ld %ld %d %d %d\n",q->name,q->ctime,q->dtime,q->password,q->ac,q->id,q->money,q->mark,q->mark1);
    fclose(fp);
    system("cls");
	cout<<"�����ɹ��������Ʊ�������˺ţ�"<<q->ac<<endl ;
	
}
    

	
