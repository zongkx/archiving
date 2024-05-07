/*select Orders.OrderID,Customers.ContactName,Customers.City,Customers.Address,Customers.Phone,Orders.Freight,
		ODD.Quantity as '订货量',ODD.Total as '总金额',ODD.DisTotal as '折扣后总金额'
from Customers ,Orders ,
	(select OD.OrderID, sum(OD.Quantity), sum(OD.Quantity*OD.UnitPrice),sum(OD.Quantity*OD.UnitPrice*(1-OD.Discount)) 
		from [Order Details] as OD Group by OD.OrderID)
	as ODD(OrderID,Quantity,Total,DisTotal)
where Orders.OrderDate between '1997-10-1' and '1997-10-7' and Orders.OrderID = ODD.OrderID and Orders.CustomerID = Customers.CustomerID
order by Customers.City,Orders.OrderDate
*/
/*
select Employees.EmployeeID,OD.Total as '订单量' 
from Employees 
left outer join
	(select Orders.EmployeeID,count(Orders.EmployeeID) 
	 from Orders  
	 where Orders.OrderDate between '1997-10-1' and '1997-10-7' group by Orders.EmployeeID
	 )as OD(EmployeeID,Total) 
on (Employees.EmployeeID = OD.EmployeeID);*/
/*select Products.ProductID,Products.ProductName,Products.UnitsInStock 
from Orders ,[Order Details] OD,Products
where Orders.OrderDate = '1997-10-7'and Orders.OrderID = OD.OrderID and OD.ProductID = 	Products.ProductID?*/

/*
select Orders.OrderID,Orders.OrderDate,Orders.RequiredDate,Products.ProductName,OD.UnitPrice,
		OD.Quantity,OD.Quantity*OD.UnitPrice*(1-Discount) 折扣后金额
from Products ,Orders ,[Order Details] OD
where Orders.CustomerID='BSBEV' and OrderDate='1997-5-16' and Orders.OrderID=OD.OrderID and
		 OD.ProductID=Products.ProductID*/
		 
		 /*
select P.ProductID,P.ProductName,P.UnitPrice,P.CategoryID,PP.CategoryID
from Products P,Products PP 
where P.CategoryID = PP.CategoryID 
group by P.ProductID,P.ProductName,P.UnitPrice,P.CategoryID,PP.CategoryID
Having P.UnitPrice>=AVG(PP.UnitPrice)
order by P.ProductID ASC*/

select Customers.CustomerID,Customers.ContactName,Customers.Phone,Customers.Country 
from Customers ,(select Orders.CustomerID,sum(OD.Quantity*OD.UnitPrice) 
	from Orders,[Order Details] OD 
	where Orders.OrderID = OD.OrderID group by Orders.CustomerID) as ODD(CustomerID,Total)
where Customers.CustomerID = ODD.CustomerID and ODD.Total>40000 
order by Customers.Country