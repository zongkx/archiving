#include<stdio.h> 
#include<malloc.h>
#include<string.h> 
#include<stdlib.h> 
#include<iostream> 
using namespace std;

typedef struct cosnode{
	char name[20];//���� 
	char ID[6];//��Ա����
	double consume;//���ѽ��  
	double s;//���� 
	double y;//�ۿ� 
	struct cosnode *next;
	}cosnode;
	
typedef  struct link{
    cosnode a[100];//������ 
	struct link *next;
}link,*linklist;
	

	
void display();//���˵� 
void create();//��ӿͻ���Ϣ
void serch();//��ѯ�ͻ���Ϣ 
void change();//�޸Ŀͻ���Ϣ 
void remove();//ɾ���ͻ���Ϣ 
void sub(); //ͳ�ƻ��ּ��ۿ�
void sort();//��������ʾ���� 
void ShellSort(cosnode p[],int sum) ;//ϣ������ 
double display_discount(double points);//�����ۿ� 
void read(linklist &head);//��ȡ�ļ����� 
	
	
	
	
