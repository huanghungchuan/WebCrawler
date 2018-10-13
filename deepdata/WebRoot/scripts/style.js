$(document).ready(function () {
        // 首页黄条根据数据不同长度不同
        $(".list1-ul").find('li').eq(0).css('border-top','1px solid #cbe4f7');
        $(".list1-ul").find('li').each(function(){
            var numbusi=$(this).find('.s-num').text();
            var swidth=numbusi/280*116;
            $(this).find('.s-rate2').width(swidth);
        });
        //tab切换div
        function showTabs(name,num,n){
            for(i=1;i<=n;i++){
                var menu=document.getElementById(name+"nav"+i);
                var con=document.getElementById(name+"con"+i);
                if(i == num){
                    $(menu).addClass("active");
                    $(con).show();
                }else{
                    $(con).css("display", "none");
                    $(menu).removeClass("active");
                }                   
            }
        }
        $("#tabs-nav").find('li').each(function(i){
                    $(this).click(function (){
                        showTabs("tabs_",i+1,20);
                    })
        });
        // 表格奇偶行颜色不同
         $('.list-tb').find('tbody').find('tr:odd').css('background','#fbfbfb');
         // 左侧菜单栏begin
         // 以下几行为初始化默认选中状态
        $(".left-menu").find(".menu1").next("ul").hide();//所有二级菜单默认收起
        $(".left-menu").find(".menu1").eq(0).next("ul").show();  //默认展开第一个二级菜单
        $(".left-menu").find(".menu1").eq(0).addClass("menu1-active"); //默认选中第一个一级菜单
        $(".left-menu").find(".menu1").eq(0).next("ul").find(".menu2").eq(0).addClass("menu2-active");  //默认选中第一个二级菜单

        $(".left-menu").find(".menu1").on("click",function(){
            $('.left-menu').find('.menu1').removeClass('menu1-active');
            $(".left-menu").find(".menu1").next("ul").hide();
            $(this).addClass('menu1-active');
            $(this).next("ul").show();
        });
        $(".left-menu").find(".menu2").on("click",function(){
            $('.left-menu').find('.menu2').removeClass('menu2-active');
            $(this).addClass('menu2-active');
        });
        // 左侧菜单栏end
});