#include<stdio.h>
#include<iostream>
#include<stdlib.h>
#include<memory.h>
#include<string.h>
#include<time.h>
#include<conio.h>

using namespace std;

typedef struct  point{
	char name[20];//���� 
	char ctime[20];//����ʱ�� 
	char dtime[20];//����ʱ�� 
	char password[5];//����
	long ac;//�˺� 
	long id;//���֤���� 
	int money;//��� 
	int mark;//��ʧ��� 
	int mark1;//������� 
	struct point *next;
}point,*linklist;

typedef struct node{
	long dac;//��¼�˺� 
	int year;//��¼��ȡ��ʱ��
	int month;
	int day;
	int  bmoney;//��¼��ȡ��Ǯ�� 
	char  cmark;//��ȡ���� 
	int   yue;//��¼�ϴ���� 
	int   zyue;//��¼��ǰ��Ǯ�� 
	struct node *next;
}node;

void record(long c,int a,int index,int f);
void manager();
void selfhelp(); 
void create();
void display();
void play1(); 
void find(long a,char n[],int y);//�����˺� a  ���� n[] ��� y 
void play0(); 
void deleteuser();
void change(); 
void acount();
void look(long a);//�����˺� a 
void loss();
int check(long a);//�����˺� a 
int check1(long a);
void census();//ͳ�Ƶ�����Ŀ 
  
