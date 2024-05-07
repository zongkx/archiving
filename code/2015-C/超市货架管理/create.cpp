#include"supermarket.h"
using namespace std;

void create(){
    system("cls");
	int i; linklist q;char a[20]; q=new link;
	
	FILE *fp;
	fp=fopen("supermarket.txt","a+");
	
 PRN:   cout<<"------------------请输入编号------------------"<<endl; 
	cin>>q->a->id ;	

    int b=check(q->a->id); 
    if(b==1)  {cout<<"您输入的编号已经存在，请重新输入！\n";goto PRN;
    }
	cout<<"------------------请输入名称------------------"<<endl;
	cin>>q->a->name;
	cout<<"------------------请输入进价------------------"<<endl;
	cin>>q->a->iprice;
	cout<<"------------------请输入售价------------------"<<endl;
    cin>>q->a->oprice;
	cout<<"------------------请输入数目------------------"<<endl; 
	cin>>q->a->number;
    a[20]=time(a);
	strcpy(q->a->itime,a); 

	q->next=NULL;
    fprintf(fp,"%d %s %.2f %.2f %d %s\n",q->a->id,q->a->name,q->a->iprice,q->a->oprice,q->a->number,q->a->itime);
    fclose(fp);
    
	cout<<endl<<"创建成功!"; 
    system("pause"); 
}
    

	
