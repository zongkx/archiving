#include<stdio.h>
#include<iostream>
#include<stdlib.h>
#include<memory.h>
#include<string.h>
#include<time.h>
//#include<graphics.h>
#include<conio.h>
using namespace std;

typedef struct  point{	
    int id;//编号 
	char name[20];//商品名
    double iprice;//进价 
	double oprice;//售价 
    int number;//余量 
    char itime[20];//入库时间 
}point;

typedef struct link{
    point a[100];
	struct link *next;
}link,*linklist;

int check(int a); 
char time(char d[]); 
void manager();
void create();
void display();
void play0(); 
void deleteuser();
void acount();
void census();
void look();
void add();
void change();
void record(int did,int a,int n);
void  move(linklist &head);
int Partition(point k[],int low,int high);
void Qsort(point k[], int low, int high);
void QuickSort(point k[],int n);


