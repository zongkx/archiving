#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>
#include<conio.h>
#include<iostream>
using namespace std;

/*��¼�û���Ϣ*/ 
 typedef struct user{
 	char uname[20];//��¼���� 
 	long  uid;//��¼id 
 	char sex;//��¼�Ա� 
 	char time[20];//��¼����ʱ�� 
    struct user *next;	 	  
 }user;
 
 /*��¼�˵���Ϣ*/ 
 typedef struct menu{
 	int  no;//��¼�˵ı�� 
	char mname[20];//��¼����
	int money;//��¼�˼� 
	int  mark;//��¼������� 
	struct menu *next;  
 }menu;
 
 /*��¼����Ա��Ϣ*/ 
 typedef struct man{
 	 
	char name[20];//����Ա���� 
	int  id;//����Աid
	char pswd[5]; //����Ա���� 
	struct man *next;  
 }man,*lman;
 
 
/*��¼��Ŀ*/
typedef struct count{
	int year;
	int month;
	int day;
	int spending;//���Ѷ�
    struct acount *next;
}count;

void cen();//������Ŀͳ��
void display();
void play0();
void play1();
void play3();
void server();
void manager();
void create1();
void menu1();//�˵� 
void sta();//ͳ�� 
void order();//��� 
void look1();//�˿���Ϣ�鿴 
void look2();//���ּ����۲鿴 
void enter();//�˿���Ϣ¼�� 
void selfhelp();//�˿��������� 
void grade();//�˿����� 
void advice();//�˿ͽ��� 

