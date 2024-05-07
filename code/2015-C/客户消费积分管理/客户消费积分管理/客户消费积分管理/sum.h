#include<stdio.h> 
#include<malloc.h>
#include<string.h> 
#include<stdlib.h> 
#include<iostream> 
using namespace std;

typedef struct cosnode{
	char name[20];//姓名 
	char ID[6];//会员卡号
	double consume;//消费金额  
	double s;//积分 
	double y;//折扣 
	struct cosnode *next;
	}cosnode;
	
typedef  struct link{
    cosnode a[100];//数据项 
	struct link *next;
}link,*linklist;
	

	
void display();//主菜单 
void create();//添加客户信息
void serch();//查询客户信息 
void change();//修改客户信息 
void remove();//删除客户信息 
void sub(); //统计积分及折扣
void sort();//按积分显示排序 
void ShellSort(cosnode p[],int sum) ;//希尔排序 
double display_discount(double points);//计算折扣 
void read(linklist &head);//读取文件函数 
	
	
	
	
