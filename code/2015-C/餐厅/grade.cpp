#include "res.h"

void grade()
{
	char a;
	int b;
	cout<<"��ӭʹ�ò�Ʒ����ϵͳ��"<<endl;
PRN:	printf("���ַ�ΪA/B/C������\n������˵ı�ţ�");
    cin>>b;
	cout<<"������������ۣ�"; 
	cin>>a;
	FILE *fp=fopen("grade.txt","a");
	fprintf(fp,"%d %c\n",b,a);
	fclose(fp);
	int i;
	cout<<"��������밴 1  �˳� 0"<<endl;
	cin>>i;
	if(i==1)
	  goto PRN; 
	
	
	
	
	
}
