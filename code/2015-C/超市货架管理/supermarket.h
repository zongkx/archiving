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
    int id;//��� 
	char name[20];//��Ʒ��
    double iprice;//���� 
	double oprice;//�ۼ� 
    int number;//���� 
    char itime[20];//���ʱ�� 
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


