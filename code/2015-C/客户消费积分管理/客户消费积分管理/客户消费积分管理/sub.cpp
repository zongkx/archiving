#include"sum.h"

//ͳ�ƻ��ּ��ۿ�
void sub()
{
	system("cls");
    linklist head;	
	read(head);//��ȡ���� 

PRN:    cout<<"\n���������Ļ�Ա����:"<<endl;
	char id[6];
	scanf("%s",id);
    linklist p,p1=head;
    while(p1->next&&strcmp(p1->next->a->ID ,id)!=0)
       {
       	 p1=p1->next;//��ǰ��㲻�ǣ�ָ����� 
	   }
    if (p1==NULL)
	   {
	   	printf("����������������룡\n");
	   	goto PRN;
	   } 
    else
	{ 
	  p=p1->next;
	  printf("��֮ǰ�Ļ���Ϊ%.1f\n",p->a->s) ;
      double y; 
      y=display_discount(p->a->s);
	
      printf("�����������Ļ��������ε��ۿ�Ϊ%.1f��\n",y);
      printf("���������������ѽ�\n") ;
      double m,a,money;cin>>m;
      money=m*y/10 ;
      printf("�����ε��ۿۼ�Ϊ��%.1f\n",money);
      a=(int)money;
      printf("���λ��֣�%.1f\n",a);
      p->a->s+=a;
      p->a->consume+=money;
     
  
	  FILE *fp2;
      fp2=fopen("costomer.txt","w");
      linklist q1=head->next;
      while(q1)
	{
	  fprintf(fp2,"%s %s %.2f %.1f %.1f\n",q1->a->name ,q1->a->ID ,q1->a->consume ,q1->a->s,q1->a->y);
	  q1=q1->next ;
	}
    fclose(fp2);
    
    cout<<"���ز˵��밴0��" ;
     int d;cin>>d;	
	    if(d==0)
        display();
}
}
