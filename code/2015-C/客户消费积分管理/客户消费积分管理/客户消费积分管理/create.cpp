#include"sum.h"

//��ӿͻ���Ϣ
void create()
{
	system("cls");
	linklist p; 
    p=new link;    /*����յĽڵ�ռ�*/ 
    FILE *fp=fopen("costomer.txt","a");//�����д�ļ�
    cout<<"������������"<<endl;
	cin>>p->a->name;
	cout<<"�������Ա���ţ�(6λ)"<<endl;
	cin>>p->a->ID;
	cout<<"�����뱾�����ѽ�"<<endl;
	cin>>p->a->consume;
	p->a->s=(int)p->a->consume;
	printf("���λ���Ϊ��%.1f\n",p->a->s);
	p->a->y=display_discount(p->a->s);
	cout<<"���ĵ�ǰ�ۿ�Ϊ��"<<p->a->y<<endl;
	p->next =NULL; 
    fprintf(fp,"%s %s %.2f %.1f %.1f\n",p->a->name ,p->a->ID ,p->a->consume,p->a->s,p->a->y);
    fclose(fp);
    printf("������� 1   ���� 0:"); 
	int a;cin>>a;
	if(a==1)
	create();
	if(a==0)
    display();
}
