#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>
#include<conio.h>
#include<iostream>
using namespace std;

/*记录用户信息*/ 
 typedef struct user{
 	char uname[20];//记录名字 
 	long  uid;//记录id 
 	char sex;//记录性别 
 	char time[20];//记录创建时间 
    struct user *next;	 	  
 }user;
 
 /*记录菜单信息*/ 
 typedef struct menu{
 	int  no;//记录菜的编号 
	char mname[20];//记录菜名
	int money;//记录菜价 
	int  mark;//记录被点次数 
	struct menu *next;  
 }menu;
 
 /*记录管理员信息*/ 
 typedef struct man{
 	 
	char name[20];//管理员姓名 
	int  id;//管理员id
	char pswd[5]; //管理员密码 
	struct man *next;  
 }man,*lman;
 
 
/*记录账目*/
typedef struct count{
	int year;
	int month;
	int day;
	int spending;//消费额
    struct acount *next;
}count;

void cen();//饭店账目统计
void display();
void play0();
void play1();
void play3();
void server();
void manager();
void create1();
void menu1();//菜单 
void sta();//统计 
void order();//点餐 
void look1();//顾客信息查看 
void look2();//评分及评价查看 
void enter();//顾客信息录入 
void selfhelp();//顾客自助服务 
void grade();//顾客评分 
void advice();//顾客建议 

