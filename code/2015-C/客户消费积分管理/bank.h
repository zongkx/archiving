#include<stdio.h>
#include<iostream>
#include<stdlib.h>
#include<memory.h>
#include<string.h>
#include<time.h>
#include<conio.h>

using namespace std;

typedef struct  point{
	char name[20];//姓名 
	char ctime[20];//创建时间 
	char dtime[20];//销户时间 
	char password[5];//密码
	long ac;//账号 
	long id;//身份证号码 
	int money;//余额 
	int mark;//挂失标记 
	int mark1;//销户标记 
	struct point *next;
}point,*linklist;

typedef struct node{
	long dac;//记录账号 
	int year;//记录存取款时间
	int month;
	int day;
	int  bmoney;//记录存取款钱数 
	char  cmark;//存取款标记 
	int   yue;//记录上次余额 
	int   zyue;//记录当前总钱数 
	struct node *next;
}node;

void record(long c,int a,int index,int f);
void manager();
void selfhelp(); 
void create();
void display();
void play1(); 
void find(long a,char n[],int y);//传递账号 a  密码 n[] 余额 y 
void play0(); 
void deleteuser();
void change(); 
void acount();
void look(long a);//传递账号 a 
void loss();
int check(long a);//传递账号 a 
int check1(long a);
void census();//统计当月账目 
  
