#include"sum.h"


//º∆À„’€ø€ 
double display_discount(double points)
{
      double discount;
       if(points<=500)  
	  discount = 10.0;   
       if(points>500&&points<=1000)  
      discount = 9.8;   
	   if(points>1000&&points<=2000) 
      discount = 9.5;   
	   if(points>2000&&points<=5000)  
      discount = 9.0;   
	   if(points>5000&&points<=7000) 
	  discount = 8.8;
	   if(points>7000&&points<=10000)
	  discount = 8.5;
	   if(points>10000) 
	  discount = 8.0;
	  return discount;
} 
	  
	  
 
	  
