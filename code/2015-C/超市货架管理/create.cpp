#include"supermarket.h"
using namespace std;

void create(){
    system("cls");
	int i; linklist q;char a[20]; q=new link;
	
	FILE *fp;
	fp=fopen("supermarket.txt","a+");
	
 PRN:   cout<<"------------------��������------------------"<<endl; 
	cin>>q->a->id ;	

    int b=check(q->a->id); 
    if(b==1)  {cout<<"������ı���Ѿ����ڣ����������룡\n";goto PRN;
    }
	cout<<"------------------����������------------------"<<endl;
	cin>>q->a->name;
	cout<<"------------------���������------------------"<<endl;
	cin>>q->a->iprice;
	cout<<"------------------�������ۼ�------------------"<<endl;
    cin>>q->a->oprice;
	cout<<"------------------��������Ŀ------------------"<<endl; 
	cin>>q->a->number;
    a[20]=time(a);
	strcpy(q->a->itime,a); 

	q->next=NULL;
    fprintf(fp,"%d %s %.2f %.2f %d %s\n",q->a->id,q->a->name,q->a->iprice,q->a->oprice,q->a->number,q->a->itime);
    fclose(fp);
    
	cout<<endl<<"�����ɹ�!"; 
    system("pause"); 
}
    

	
