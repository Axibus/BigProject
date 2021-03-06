<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.Query" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.springframework.*" %>
<%@ page import="entity.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
    <head>
    
    
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<base href="<%=basePath%>">  
    
    
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>List-View</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		
        <!-- all css here -->
        <!-- bootstrap v3.3.6 css -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- animate css -->
        <link rel="stylesheet" href="css/animate.css">
        <!-- jquery-ui.min css -->
        <link rel="stylesheet" href="css/jquery-ui.min.css">
        <!-- meanmenu css -->
        <link rel="stylesheet" href="css/meanmenu.min.css">
        <!-- nivo slider css -->
        <link rel="stylesheet" href="lib/css/nivo-slider.css" type="text/css" />
        <link rel="stylesheet" href="lib/css/preview.css" type="text/css" />
        <!-- owl.carousel css -->
        <link rel="stylesheet" href="css/owl.carousel.css">
        <!-- font-awesome css -->
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="style.css">
        <!-- responsive css -->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- modernizr js -->
        <script src="js/vendor/modernizr-2.8.3.min.js"></script>
    </head>
    <body>

<%
String st = (String)request.getAttribute("pt");
String selecttype="";
if(st==null){
	selecttype="";
}else{
	selecttype=st;
	
}
String ordermethod=(String)request.getAttribute("m");
System.out.println(selecttype);
System.out.println(ordermethod);
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection(
		"jdbc:mysql://127.0.0.1:3306/bigproject?useUnicode=true&characterEncoding=UTF-8","root","");

List<Product> list=new ArrayList<Product>();
PreparedStatement pstm=con.prepareStatement("select * from product"+selecttype+" order by "+ordermethod);
ResultSet rs=pstm.executeQuery();
while(rs.next()){
	Product p=new Product();
	p.setId(rs.getInt(1));
	p.setName(rs.getString(2));
	p.setPrice(rs.getDouble(4));
	p.setImg1(rs.getString(5));
	p.setImg2(rs.getString(6));
	p.setImg3(rs.getString(7));
	p.setIntro(rs.getString(8));
	list.add(p);
}

//PreparedStatement pidcon=con.prepareStatement("select * from producttype");
//ResultSet pidrs=pstm.executeQuery();

con.close();
request.setAttribute("list",list);



%>




        <!--header top area start-->
        <div>你好呀，${user.getUserName() }</div>
        <div class="header_area">
            <div class="header_border">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                            <div class="header_heaft_area">
                                <div class="header_left_all">
                                    <div class="mean_al_dv">
                                        <div class="littele_menu"><a href="#">Language: English <i class="fa fa-caret-down"></i></a> </div>
                                        <ul class="option">
                                            <li><a href="#">France</a></li>
                                            <li><a href="#">Germany</a></li>
                                            <li><a href="#">Japanese</a></li>
                                        </ul>
                                    </div>
                                    <div class="usd_area">
                                        <div class="littele_menu"><a href="#">
                                            Currency: USD
                                            <i class="fa fa-caret-down"></i>
                                            </a>
                                        </div>
                                        <ul class="option">
                                            <li><a href="#">EUR - Euro</a></li>
                                            <li><a href="#">GBP - British Pound</a></li>
                                            <li><a href="#">INR - Indian Rupee</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                            <div class="header_right_area">
                                <ul>
                                    <li>
                                        <a class="account" href="#">My Account</a>
                                    </li>
                                    <li>
                                        <a class="wishlist" href="#">wishlist</a>
                                    </li>
                                    <li>
                                        <a class="Shopping cart" href="#">Shopping cart</a>
                                    </li>
                                    <li>
                                        <a class="Checkout" href="#">Checkout</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--header top area end-->
            <!--header middle area start-->
            <div class="header_middle">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="logo_area">
                                <a href="index.html"><img src="img/logo-pic/logo.png" alt="" /></a>
                            </div>
                        </div>
                        <div class="col-md-9">
                            <div class="header_all search_box_area">
                                <form class="new_search" role="search" method="get" action="#">
                                    <input id="mix_search" class="search-field" placeholder="Search Products…" value="" name="s" title="Search for:" type="search">
                                    <input value="Search" type="submit">
                                    <input name="post_type" value="product" type="hidden">
                                </form>
                            </div>
                            <div class="header_all shopping_cart_area">
                                <div class="widget_shopping_cart_content">
                                    <div class="topcart">
                                        <a class="cart-toggler" href="">
                                        <i class="icon"></i>
                                        <span class="my-cart">Shopping cart</span>
                                        <span class="qty">2 Items</span>
                                        <span class="fa fa-angle-down"></span>
                                        </a>
                                        <div class="new_cart_section">
                                            <ol class="new-list">
                                                <!-- single item -->
                                                <li class="wimix_area">
                                                    <a class="pix_product" href="">
                                                    <img alt="" src="img/product-pic/7-150x98.jpg">
                                                    </a>
                                                    <div class="product-details">
                                                        <a href="#">Adipiscing cursus eu</a>
                                                        <span class="sig-price">1×$300.00</span>
                                                    </div>
                                                    <div class="cart-remove">
                                                        <a class="action" href="#">
                                                        <i class="fa fa-close"></i>
                                                        </a>
                                                    </div>
                                                </li>
                                                <!-- single item -->
                                                <!-- single item -->
                                                <li class="wimix_area">
                                                    <a class="pix_product" href="#">
                                                    <img alt="" src="img/product-pic/1-150x98.jpg">
                                                    </a>
                                                    <div class="product-details">
                                                        <a href="#">Duis convallis</a>
                                                        <span class="sig-price">1×$100.00</span>
                                                    </div>
                                                    <div class="cart-remove">
                                                        <a class="action" href="#">
                                                        <i class="fa fa-close"></i>
                                                        </a>
                                                    </div>
                                                </li>
                                                <!-- single item -->
                                            </ol>
                                            <div class="top-subtotal">
                                                Subtotal: <span class="sig-price">$400.00</span>
                                            </div>
                                            <div class="cart-button">
                                                <ul>
                                                    <li>
                                                        <a href="cartview">
                                                        View my cart
                                                        <i class="fa fa-angle-right"></i>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                        Checkout
                                                        <i class="fa fa-angle-right"></i>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--header bottom area start-->
            <div class="all_menu_area">
                <div class="menu_inner">
                    <div class="container">
                        <div class="full_menu clearfix">
                            <div class="new_menu">
                                <div class="drp-menu">
                                    <div class="col-md-3 pr pl">
                                        <div class="all_catagories">
                                            <div class="enable_catagories">
                                                <i class="fa fa-bars"></i>
                                                <span>All Categories</span>
                                                <i class="fa fa-angle-down"></i>
                                            </div>
                                        </div>
                                        <div class="catagory_menu_area">
                                            <div class="catagory_mega_menu">
                                                <div class="cat_mega_start">
                                                    <ul class="list">
                                                        <li class="next_area">
                                                            <a class="item_link" href="#">
                                                            <i class="fa fa-television"></i>
                                                            <span class="link_content">
                                                            <span class="link_text">
                                                            Electronics
                                                            <span class="link_descr">Praesent accumsan elementum maximus </span>
                                                            </span>
                                                            </span>
                                                            </a>
                                                            <ul class="electronics_drpdown">
                                                                <li class="parent">
                                                                    <a href="#"></a>
                                                                    <div class="mega_menu">
                                                                        <div class="mega_menu_coloumn">
                                                                            <ul>
                                                                                <li><a href="#">Men's</a></li>
                                                                                <li><a href="#">Hats</a></li>
                                                                                <li><a href="#">Mirriors</a></li>
                                                                                <li><a href="#">Singles</a></li>
                                                                            </ul>
                                                                        </div>
                                                                        <div class="mega_menu_coloumn">
                                                                            <ul>
                                                                                <li><a href="#">Sports& Outdoors</a></li>
                                                                                <li><a href="#">Smartphones</a></li>
                                                                                <li><a href="#">Womens</a></li>
                                                                                <li><a href="#">Health & Beauty</a></li>
                                                                            </ul>
                                                                        </div>
                                                                        <div class="mega_menu_coloumn">
                                                                            <ul>
                                                                                <li class="mega_content">
                                                                                    <a href="#">Accessories</a>
                                                                                </li>
                                                                                <li><a href="#">Hobbies</a></li>
                                                                                <li><a href="#">networking</a></li>
                                                                                <li><a href="#">accessories</a></li>
                                                                                <li><a href="#">electronics</a></li>
                                                                            </ul>
                                                                        </div>
                                                                        <div class="mega_menu_coloumn">
                                                                            <ul>
                                                                                <li><a href="#">Laptops & Accessories</a></li>
                                                                                <li><a href="#">hoodies</a></li>
                                                                                <li><a href="#">watches</a></li>
                                                                                <li><a href="#">flashlights</a></li>
                                                                                <li><a href="#">mirriors</a></li>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                        <li class="differ_sec_area">
                                                            <a class="item_link" href="#">
                                                            <i class="fa fa-mobile"></i>
                                                            <span class="link_content">
                                                            <span class="link_text">
                                                            Smartphone & Tablets
                                                            <span class="link_descr">Praesent accumsan elementum maximus </span>
                                                            </span>
                                                            </span>
                                                            </a>
                                                            <ul class="another_drop_menu">
                                                                <li class="discrip">
                                                                    <a class="new_link_2 with_icon" href="#" tabindex="1">Posters</a>
                                                                    <ul class="new_miup_menu">
                                                                        <li>
                                                                            <ul class="new_mixup_tm">
                                                                                <li class="exact">
                                                                                    <a class="new_link_8 with_icon" href="#" tabindex="1">Cocktail</a>
                                                                                    <ul class="decent_mean_menu">
                                                                                        <li>
                                                                                            <ul class="new_mixup_tm">
                                                                                                <li>
                                                                                                    <a href="#"></a>Cost & Jackets
                                                                                                </li>
                                                                                            </ul>
                                                                                        </li>
                                                                                    </ul>
                                                                                </li>
                                                                                <li><a href="#">Furniture</a></li>
                                                                            </ul>
                                                                        </li>
                                                                    </ul>
                                                                </li>
                                                                <li class="risk">
                                                                    <a class="new_link_2 new_link_3 with_icon" href="#" tabindex="1">Watches
                                                                    </a>
                                                                    <ul class="new_miup_menu">
                                                                        <li>
                                                                            <ul class="new_mixup_tm">
                                                                                <li><a href="#">Women's</a></li>
                                                                            </ul>
                                                                        </li>
                                                                    </ul>
                                                                </li>
                                                                <li><a href="#">Pendants</a></li>
                                                                <li><a href="#">Chocklates</a></li>
                                                            </ul>
                                                        </li>
                                                        <li class="differ_sec_area">
                                                            <a class="item_link " href="#">
                                                            <i class="fa fa-gift"></i>
                                                            <span class="link_content">
                                                            <span class="link_text">
                                                            Health & Beauty
                                                            <span class="link_descr">Praesent accumsan elementum maximus</span>
                                                            </span>
                                                            </span>
                                                            </a>
                                                            <ul class="another_drop_menu">
                                                                <li class="discrip">
                                                                    <a class="new_link_2 new_link_4 with_icon" href="#" tabindex="1">
                                                                    <i class="fa fa-futbol-o"></i>
                                                                    Sports & Outdoors
                                                                    </a>
                                                                </li>
                                                                <li class="discrip">
                                                                    <a class="new_link_2 new_link_4 with_icon" href="#" tabindex="1">
                                                                    <i class="fa fa-bullseye"></i>
                                                                    Bags, Shoes & Accessories
                                                                    </a>
                                                                </li>
                                                                <li class="discrip"><a class="new_link_2 new_link_4 with_icon" href="#" tabindex="1"><i class="fa fa-file-image-o"></i>
                                                                    Toys & Hobbies</a>
                                                                </li>
                                                                <li class="discrip">
                                                                    <a class="new_link_2 new_link_4 with_icon" href="#" tabindex="1"><i class="fa fa-television"></i>
                                                                    Computer & Networking</a>
                                                                </li>
                                                                <li class="discrip">
                                                                    <a class="new_link_2 new_link_4 with_icon" href="#" tabindex="1">
                                                                    <i class="fa fa-laptop"></i>
                                                                    Laptops & Accessories
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                        <li>
                                                            <a class="item_link item_link_2" href="#">
                                                            <i class="fa fa-heart"></i>
                                                            <span class="link_content">
                                                            <span class="link_text">
                                                            Jewelry & Watches
                                                            <span class="link_descr">Praesent accumsan elementum maximus</span>
                                                            </span>
                                                            </span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a class="item_link item_link_2" href="#">
                                                            <i class="fa fa-star"></i>
                                                            <span class="link_content">
                                                            <span class="link_text">
                                                            Flashlights & Lamps
                                                            <span class="link_descr">Praesent accumsan elementum maximus</span>
                                                            </span>
                                                            </span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a class="item_link item_link_2" href="#">
                                                            <i class="fa fa-lightbulb-o"></i>
                                                            <span class="link_content">
                                                            <span class="link_text">
                                                            Headlight
                                                            <span class="link_descr">Praesent accumsan elementum maximus</span>
                                                            </span>
                                                            </span>
                                                            </a>
                                                        </li>
                                                        <li class="cost-menu">
                                                            <a class="item_link item_link_2" href="#">
                                                            <i class="fa fa-pie-chart"></i>
                                                            <span class="link_content">
                                                            <span class="link_text">
                                                            Cost & jackets
                                                            <span class="link_descr">Praesent accumsan elementum maximus </span>
                                                            </span>
                                                            </span>
                                                            </a>
                                                        </li>
                                                        <li class="showmore-items shwitm">
                                                            <i class="fa fa-plus-square-o"></i>
                                                            <span>More Categories</span>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--menu area start-->
                                    <div class="col-md-9 pl">
                                        <div class="menu_area">
                                            <div class="menu">
                                                <nav>
                                                    <ul>
                                                        <li><a href="index">Home</a></li>
														<li><a href="vieworder">ViewOrder</a></li>
														<li><a href="viewcart">Cart</a></li>
														<li><a href="productlist">List</a></li>
														<li><a href="account">Account</a></li>
                                                    </ul>
                                                </nav>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--header area end-->
        <!-- mobile-menu-area-start -->
        <div class="mobile-menu-area hidden-md hidden-lg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="mobile-menu">
                            <nav id="mobile-menu-active">
                                <ul id="nav">
                                    <li><a href="index">Home</a></li>
														<li><a href="vieworder">ViewOrder</a></li>
														<li><a href="viewcart">Cart</a></li>
														<li><a href="productlist">List</a></li>
														<li><a href="account">Account</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- mobile-menu-area-end -->
        <!--social design arae start-->
        <div class="link_area">
            <div class="container">
                <div class="row">
                    <div class="social_design on_right">
                        <ul>
                            <li>
                                <a class="facebook" target="_blank" href="#facebook">
                                <span>
                                <i class="fa fa-facebook"></i>
                                <span class="social-text">Follow via Facebook</span>
                                </span>
                                </a>
                            </li>
                            <li>
                                <a class="twitter" target="_blank" href="#twitter.com">
                                <span>
                                <i class="fa fa-twitter"></i>
                                <span class="social-text">Follow via Twitter</span>
                                </span>
                                </a>
                            </li>
                            <li>
                                <a class="google-plus" target="_blank" href="#google-plus">
                                <span>
                                <i class="fa fa-google-plus"></i>
                                <span class="social-text">Follow via Google +</span>
                                </span>
                                </a>
                            </li>
                            <li>
                                <a class="youtube" target="_blank" href="#youtube">
                                <span>
                                <i class="fa fa-youtube"></i>
                                <span class="social-text">Follow via Youtube</span>
                                </span>
                                </a>
                            </li>
                            <li>
                                <a class="pinterest" target="_blank" href="#pinterest">
                                <span>
                                <i class="fa fa-pinterest"></i>
                                <span class="social-text">Follow via Pinterest</span>
                                </span>
                                </a>
                            </li>
                            <li>
                                <a class="mail-to" target="_blank" href="mailto:lionthemes88@gmail.com">
                                <span>
                                <i class="fa fa-envelope-o"></i>
                                <span class="social-text">Mail To Us</span>
                                </span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!--social design arae end-->
        <!--shop area start-->
        <div class="shop_area">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="shop_menu">
                            <ul class="cramb_area cramb_area_5">
                                <li><a href="index.html">Home /</a></li>
                                <li class="br-active"><a href="#">Shop</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--bar area start-->
                <div class="row">
                    <div class="col-md-3">
                        <div class="category-widget-menu">
                            <h2 class="cat-menu-title text-uppercase">categories</h2>
                            <div class="menu-categories-container">
                                <ul class="menu-categories">
                                    <li><a href="#">Accessories</a></li>
                                    <li class="expandable">
                                        <a href="#">Electronic</a>
                                        <ul class="category-sub">
                                            <li><a href="#">Hoodies (17)</a></li>
                                            <li><a href="#">T-shirts (3)</a></li>
                                        </ul>
                                    </li>
                                    <li class="expandable">
                                        <a href="#">Headlight</a>
                                        <ul class="category-sub">
                                            <li><a href="#">Hats (10)</a></li>
                                        </ul>
                                    </li>
                                    <li class="expandable">
                                        <a href="#">Mirriors (12)</a>
                                        <ul class="category-sub">
                                            <li><a href="#">Albums(1)</a></li>
                                            <li><a href="#">Singles(3)</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Posters</a></li>
                                    <li><a href="#">Electronic</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="category-widget-menu">
                                    <h2 class="cat-menu-title text-uppercase">size</h2>
                                    <div class="menu-categories-container">
                                        <ul class="menu-categories">
                                            <li><a href="#">L (1)</a></li>
                                            <li><a href="#">M (1)</a></li>
                                            <li><a href="#">S (1)</a></li>
                                            <li><a href="#">XL (1)</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="category-widget-menu">
                                    <h2 class="cat-menu-title text-uppercase">color</h2>
                                    <div class="menu-categories-container">
                                        <ul class="menu-categories">
                                            <li><a href="#">Gold (1)</a></li>
                                            <li><a href="#">Green (1)</a></li>
                                            <li><a href="#">White (1)</a></li>
                                            <li><a href="#">Yellow (1)</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="single-sidebar-dr">
                                    <aside class="single-sidebar">
                                        <h3>Filter By Price</h3>
                                        <div class="info_widget">
                                            <div class="price_filter">
                                                <div id="slider-range"></div>
                                                <div class="price_slider_amount">
                                                    <input type="text" id="amount" name="price"  placeholder="Add Your Price">
                                                </div>
                                                <div class="amount-range">
                                                    <p>Price:</p>
                                                    <input type="submit"  value="Filter">
                                                </div>
                                            </div>
                                        </div>
                                    </aside>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="single-sidebar-dr">
                                    <aside class="single-sidebar single-sidebar-2 single-sidebar-c3 ">
                                        <h3>Compare</h3>
                                        <ul class="products-list" data-lang="">
                                            <li class="list_empty">No products to compare</li>
                                        </ul>
                                        <div class="amount-range">	
                                            <a class="clear-all" href="#" data-product_id="all">Clear all</a> 
                                            <input type="submit" value="COMPARE">
                                        </div>
                                    </aside>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="single-sidebar-dr">
                                    <aside class="single-sidebar single-sidebar-vg">
                                        <h3>Products Tags</h3>
                                        <div class="compare_content compare_content_2">
                                            <div class="new_tag">
                                                <a href="#">Clothing</a>
                                                <a href="#">Enim</a>
                                                <a href="#">Fashion</a>
                                                <a href="#">Glasses</a>
                                                <a href="#">Hats</a>
                                                <a href="#">Hoodies</a>
                                            </div>
                                        </div>
                                    </aside>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="category-widget-menu">
                                    <h2 class="cat-menu-title text-uppercase">On Sale</h2>
                                    <div class="all-pros-ex all-pros-ex-2">
                                        <div class="llc_pro">
                                            <div class="sinle_pic_3">
                                                <a href="#">
                                                <img class="primary-img" src="img/product-pic/product_pic_7.jpg" alt="" />
                                                </a>
                                            </div>
                                        </div>
                                        <div class="product_content_2">
                                            <div class="usal_pro">
                                                <div class=" product_name_new">
                                                    <h2>
                                                        <a href="#">Cras nec nisl ut era</a>
                                                    </h2>
                                                </div>
                                                <div class="product_price product_price_new product_price_new_3">
                                                </div>
                                                <div class="price_box price_box_new price_box_new_3">
                                                    <span class="old- price old- price-3">$250.00</span>
                                                    <span class="spical-price">$200.00</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="all-pros-ex all-pros-ex-2">
                                        <div class="llc_pro">
                                            <div class="sinle_pic_3">
                                                <a href="#">
                                                <img class="secondary-img" src="img/top-pic/top_pic_7.jpg" alt="" />
                                                </a>
                                            </div>
                                        </div>
                                        <div class="product_content_2">
                                            <div class="usal_pro">
                                                <div class=" product_name_new">
                                                    <h2>
                                                        <a href="#">Metus nisi posuere n</a>
                                                    </h2>
                                                </div>
                                                <div class="product_price product_price_new product_price_new_3">
                                                </div>
                                                <div class="price_box price_box_new price_box_new_3">
                                                    <span class="old- price old- price-3">$250.00</span>
                                                    <span class="spical-price">$200.00</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="all-pros-ex all-pros-ex-2">
                                        <div class="llc_pro">
                                            <div class="sinle_pic_3">
                                                <a href="#">
                                                <img class="primary-img" src="img/top-pic/top_pic_1.jpg" alt="" />
                                                </a>
                                            </div>
                                        </div>
                                        <div class="product_content_2">
                                            <div class="usal_pro">
                                                <div class=" product_name_new">
                                                    <h2>
                                                        <a href="#">Nam fringilla</a>
                                                    </h2>
                                                </div>
                                                <div class="product_price product_price_new product_price_new_3">
                                                </div>
                                                <div class="price_box price_box_new price_box_new_3">
                                                    <span class="old- price old- price-3">$200.00</span>
                                                    <span class="spical-price">$150.00</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="bar">
                            <p class="result_show">Showing 1–15 of 21 results</p>
                            <div class="bar_box">
                               <span><a href="selectproduct/id">热门商品</a></span>	
                               <span><a href="selectproduct/id">最新商品</a></span>	
                               <span><a href="selectproduct/id desc">最热商品</a></span>	
                               <p>按分类：</p>
                               <form id="myform" action="selectprotype" method="post">
                               
                               <select name="pid" onchange="submitForm()">
                               			<option >请选择</option>
                               			<c:forEach items="${typelist }" var="pt">
                                        	<option value="${pt.getId() }">${pt.getType() }</option>
                                        </c:forEach>
                               </select>
                               
                               </form>
                               <script>
                               
                               function submitForm(){
                            	   var form= document.getElementById("myform");
                            	   form.submit();
                               }
                               
                               </script>
                               
                            </div>
                            <div class="right_area">
                                <!-- Nav tabs -->
                                <ul class="retabs" role="tablist">
                                    <li role="presentation"><a href="#home" role="tab" data-toggle="tab"><i class="fa fa-th"> Grid</i></a></li>
                                    
                                </ul>
                            </div>
                        </div>
                        <div class="tab-content">
                        
                        <!-- 商品 -->
                            <div role="tabpanel" class="tab-pane active" id="home">
                            <c:forEach items="${requestScope.list }" var="p">
                            	<form action="product" method="post">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="all-pros br-ntf">
                                            <div class="row">
                                                <div class="col-md-4 col-sm-4 pl pr">
                                                    <div class="sngl-pro">
                                                        <div class="single_product single_product_2 single_product_3rd">
                                                            <span>hot</span>
                                                        </div>
                                                        <div class="sinle_pic sngl-pc sinle_pic_2xd">
                                                            <a href="#">
                                                            <img class="primary-img" src="${p.getImg1() }" alt="" />
                                                            <img class="secondary-img" src="${p.getImg2() }" alt="" />
                                                            </a>
                                                        </div>
                                                    
                                                    </div>
                                                </div>
                                                <div class="col-md-8 col-sm-8 pl pr">
                                                    <div class="product_content product_content_nx">
                                                        <div class="usal_pro">
                                                            <div class="product_name_2 product_name_3 prnm">
                                                                <h2>
                                                                    <a href="productview/${p.getId() }">${p.getName() }</a>
                                                                	<p>${p.getIntro() }</p>
                                                                </h2>
                                                                <div class="pro_discrip">
                                                                    <p>${p.getProTypeId() }</p>
                                                                </div>
                                                            </div>
                                                            <div class="action actionmm">
                                                                <div class="product_price product_price_tz">
                                                                    <div class="price_rating">
                                                                        <a href="#"><i class="fa fa-star"></i></a>
                                                                        <a href="#"><i class="fa fa-star"></i></a>
                                                                        <a href="#">
                                                                        <i class="fa fa-star"></i>
                                                                        </a>
                                                                        <a href="#">
                                                                        <i class="fa fa-star"></i>
                                                                        </a>
                                                                        <a href="#">
                                                                        <i class="fa fa-star"></i>
                                                                        </a>
                                                                        <a class="not-rated" href="#"><i class="fa fa-star-o" aria-hidden="true"></i>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                                <div class="price_box price_box_tz">
                                                                    <span class="spical-price">${p.getPrice() }</span>
                                                                </div>
                                                                <div class="last_button_area">
                                                                    <ul class="add-to-links clearfix">
                                                                        <li>
                                                                            <div class="new_act">
                                                                            
                                                                            <!-- 加购物车 -->
                                                                                <!-- a class="button_act button_act_2 button_act_hts" data-quick-id="45" href="" title="" data-toggle="tooltip" data-original-title="Donec non est at">Add to Cart</a-->
                                                                                <a href="addtocart/${p.getId() }&${user.getUserId() }">加入购物车</a>
                                                                            </div>
                                                                        </li>
                                                                        
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                <!-- >td><a href="editProduct/${p.getId() }">修改</a>
								<a href="deleteproduct/${p.getId() }">删除</a></td-->
                                </div>
                                </form>
                            </c:forEach>
                       <!-- 商品结束 -->     
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="navi_area">
                                        <ul>
                                            <li class="active-2">
                                                <a href="#">1</a>
                                            </li>
                                            <li>
                                                <a href="#">2</a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                <i class="fa fa-angle-right"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--shop area end-->
        <!--brand area start-->
        <div class="logo_area">
            <div class="container">
                <div class="row">
                    <div class="brand brand-2">
                        <div class="new_product">
                            <div class="product_heading product_heading_tf">
                                <i class="fa fa-coffee"></i>
                                <span>Brand Logo</span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="main_brand main_brand_tf">
                            <div class="all_brand indicator-brand indicator-brand-7">
                                <div class="col-md-12">
                                    <div class="brand_pix">
                                        <img src="img/brand-logo/logo_1.jpg" alt="" />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="brand_pix">
                                        <img src="img/brand-logo/logo_2.jpg" alt="" />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="brand_pix">
                                        <img src="img/brand-logo/logo_3.jpg" alt="" />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="brand_pix">
                                        <img src="img/brand-logo/logo_4.jpg" alt="" />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="brand_pix">
                                        <img src="img/brand-logo/logo_5.jpg" alt="" />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="brand_pix">
                                        <img src="img/brand-logo/logo_6.jpg" alt="" />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="brand_pix">
                                        <img src="img/brand-logo/logo_3.jpg" alt="" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--brand area end-->
        <!--newsletter area start-->
        <div class="all_news_letter">
            <div class="news_letter">
                <div class="news_middele">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="news_heading">
                                    <h3> newsletter </h3>
                                </div>
                                <div class="full_form">
                                    <form id="form-newsletter1" class="widget_wysija" method="post" action="#wysija">
                                        <p class="wysija-paragraph">
                                            <input name="wysija[user][email]" class="wysija-input validate[required,custom[email]]" title="Enter Your Mail..." placeholder="Enter Your Mail..." value="" id="form-validation-field-0" style="" type="text">
                                        </p>
                                        <input class="wysija-submit wysija-submit-field" value="Subscribe!" type="submit">
                                    </form>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <div class="news_right">
                                    <div class="news_heading news_heading_3">
                                        <h3>Follow us:</h3>
                                    </div>
                                    <ul class="social-icons">
                                        <li>
                                            <a class="facebook social-icon" href="#facebook" title="" target="_blank" data-toggle="tooltip" data-original-title="Facebook">
                                            <i class="fa fa-facebook"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a class="twitter social-icon" href="#twitter.com" title="" target="_blank" data-toggle="tooltip" data-original-title="Twitter">
                                            <i class="fa fa-twitter"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a class="google-plus social-icon" href="#google-plus" title="" target="_blank" data-toggle="tooltip" data-original-title="Google-plus">
                                            <i class="fa fa-google-plus"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a class="youtube social-icon" href="#youtube" title="" target="_blank" data-toggle="tooltip" data-original-title="Youtube">
                                            <i class="fa fa-youtube"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a class="social-icon instagram" href="#" title="" target="_blank" data-toggle="tooltip" data-original-title="instagram">
                                            <i class="fa fa-instagram"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--newsletter area end-->
        <!--footer top area start-->
        <div class="footer_area">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6">
                        <div class="footer_menu">
                            <div class="news_heading_2">
                                <h3>Information </h3>
                            </div>
                            <div class="footer_menu">
                                <ul>
                                    <li>
                                        <a href="#">our blog</a>
                                    </li>
                                    <li>
                                        <a href="#">about our shop</a>
                                    </li>
                                    <li>
                                        <a href="#">secure shopping</a>
                                    </li>
                                    <li>
                                        <a href="#">privecy policy</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="footer_menu">
                            <div class="news_heading_2">
                                <h3>My Account </h3>
                            </div>
                            <div class="footer_menu">
                                <ul>
                                    <li>
                                        <a href="#">My Account</a>
                                    </li>
                                    <li>
                                        <a href="#">Wishlist</a>
                                    </li>
                                    <li>
                                        <a href="#">Shopping Cart</a>
                                    </li>
                                    <li>
                                        <a href="#">Checkout</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="footer_menu">
                            <div class="news_heading_2">
                                <h3> Our Services </h3>
                            </div>
                            <div class="footer_menu">
                                <ul>
                                    <li>
                                        <a href="#">Shipping & Returns</a>
                                    </li>
                                    <li>
                                        <a href="#">Secure Shopping</a>
                                    </li>
                                    <li>
                                        <a href="#">International Shipping</a>
                                    </li>
                                    <li>
                                        <a href="#">Affiliates</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="footer_menu footer_menu_2">
                            <div class="news_heading_2">
                                <h3> Store Information </h3>
                            </div>
                            <ul>
                                <li>
                                    <i class="fa fa-home"></i>
                                    <p>My Company : 42 avenue des Champs Elysées 75000  France</p>
                                </li>
                                <li>
                                    <i class="fa fa-phone"></i>
                                    <p>Phone: (0123) 456789</p>
                                </li>
                                <li>
                                    <i class="fa fa-envelope"></i>
                                    <p>Email: admin@hastech.company</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!--footer top area end-->
            <!--footer middle area start-->
            <div class="footer_middle">
                <div class="container">
                    <div class="fotter_inner">
                        <div class="middele_pic">
                            <img src="img/icon/payment-300x30.png" alt="" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--footer middle area end-->
        <!--footer bottom area start-->
        <div class="footer-bottom">
            <div class="container">
                <div class="widget-copyright text-center">
                    Copyright &copy; 2017.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
                </div>
            </div>
        </div>
        <!--footer bottom area end-->
        <!--modal content start-->
        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="modal-product">
                            <div class="row">
                                <div class="new_port new_port_2">
                                    <div class="port_pix">
                                        <img src="img/product-pic/product_pic_2.jpg" alt="">
                                    </div>
                                </div>
                                <div class="elav_titel elav_titel_2">
                                    <div class="elv_heading elv_heading_therteen">
                                        <h3>Donec non est at</h3>
                                    </div>
                                    <div class="elav_info">
                                        <div class="price_box price_box_pb">
                                            <span class="spical-price spical-price-nk">$250.00</span>
                                        </div>
                                        <form class="cart-btn-area cart-btn-area-dec" action="#">
                                            <a class="see-all" href="#">See all features</a><br>
                                            <input type="number" value="1">
                                            <button class="add-tocart add-tocart-2" type="submit">Add to cart</button>
                                        </form>
                                    </div>
                                    <div class="evavet_description evavet_description_dec">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce posuere metus vitae arcu imperdiet, id aliquet ante scelerisque. Sed sit amet sem vitae urna fringilla tempus.</p>
                                    </div>
                                    <div class="elavetor_social">
                                        <h3 class="widget-title">Share this product</h3>
                                        <br>
                                        <ul class="social-link social-link-bbt">
                                            <li><a class="fb" data-original-title="facebook" href="#" title="" data-toggle="tooltip"><i class="fa fa-facebook"></i></a></li>
                                            <li><a class="twit" data-original-title="twitter" href="#" title="" data-toggle="tooltip"><i class="fa fa-twitter"></i></a></li>
                                            <li><a class="pinter" data-original-title="pinterest" href="#" title="" data-toggle="tooltip"><i class="fa fa-pinterest"></i></a></li>
                                            <li><a class="google+" href="#" title="Google+" data-target="#productModal" data-toggle="tooltip"><i class="fa fa-google-plus"></i></a></li>
                                            <li><a class="lindin" href="#" title="LinkedIn" data-target="#productModal" data-toggle="tooltip"><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--modal content end-->
		
		
		
		
		
		
        <!-- all js here -->
        <!-- jquery latest version -->
        <script src="js/vendor/jquery-1.12.0.min.js"></script>
        <!-- bootstrap js -->
        <script src="js/bootstrap.min.js"></script>
        <!-- nivo slider js -->
        <script src="lib/js/jquery.nivo.slider.js" type="text/javascript"></script>
        <script src="lib/home.js" type="text/javascript"></script>
        <!-- owl.carousel js -->
        <script src="js/owl.carousel.min.js"></script>
        <!-- meanmenu js -->
        <script src="js/jquery.meanmenu.js"></script>
        <!-- jquery-ui js -->
        <script src="js/jquery-ui.min.js"></script>
        <!-- easing js -->
        <script src="js/jquery.easing.1.3.js"></script>
        <!-- mixitup js -->
        <script src="js/jquery.mixitup.min.js"></script>
        <!-- jquery.countdown js -->
        <script src="js/jquery.countdown.min.js"></script>
        <!-- wow js -->
        <script src="js/wow.min.js"></script>
        <!-- popup js -->
        <script src="js/jquery.magnific-popup.min.js"></script> 
        <!-- plugins js -->
        <script src="js/plugins.js"></script>
        <!-- main js -->
        <script src="js/main.js"></script>
    </body>
</html>