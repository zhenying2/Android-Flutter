import 'package:carousel_slider/carousel_slider.dart';
import 'package:carrot_market/components/manor_temperature_widget.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class DetailContentView extends StatefulWidget{
  Map<String,String> data;
  DetailContentView({Key? key,required this.data}) : super(key:key);

  @override
  _DetailContentViewState createState() => _DetailContentViewState();
}

class _DetailContentViewState extends State<DetailContentView>{
  late Size size;
  late List<Map<String,String>> imgList;
  late int _current;

  @override
  void didChangeDependencies(){
    super.didChangeDependencies();
    size=MediaQuery.of(context).size;
    _current=0;
    imgList=[
      {"id": "0", "url":widget.data["image"]!},
      {"id": "1", "url":widget.data["image"]!},
      {"id": "2", "url":widget.data["image"]!},
      {"id": "3", "url":widget.data["image"]!},
      {"id": "4", "url":widget.data["image"]!},
    ];
  }
  PreferredSizeWidget _appbarWidget(){
    return AppBar(
      backgroundColor: Colors.transparent,
      elevation: 0,
      leading: IconButton(onPressed: (){
        Navigator.pop(context);
      },
          icon: Icon(Icons.arrow_back,color: Colors.white)),
      actions: [
        IconButton(onPressed: () {}, icon: Icon(Icons.share,color: Colors.white)),
        IconButton(onPressed: () {}, icon: Icon(Icons.more_vert,color: Colors.white)),
      ],
    );
  }

  Widget _makeSliderImage(){
    return Container(
      child: Stack(
        children: [
          Hero(
            tag: widget.data["cid"]!,
            child:
            CarouselSlider(
              items:imgList.map((map){
                return Image.asset(
                  map["url"]!,
                  width:size.width,
                  fit: BoxFit.fill,
                );
              }).toList(),
              options: CarouselOptions(
                  height: size.width,
                  initialPage: 0,
                  enableInfiniteScroll: false,
                  viewportFraction: 1,
                  onPageChanged: (index,reason){
                    setState(() {
                      _current=index;
                    });
                  }
              ),
            ),
          ),
          Positioned(
            bottom: 0,
            left:0,
            right:0,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: imgList.map((map){
                return Container(
                  width: 10.0,
                  height: 10.0,
                  margin: EdgeInsets.symmetric(vertical: 10.0,horizontal: 2.0),
                  decoration: BoxDecoration(
                    shape: BoxShape.circle,
                    color: _current == int.parse(map["id"]!)
                        ? Colors.white : Colors.white.withOpacity(0.4),
                  ),
                );
              }).toList(),
            ),
          ),
        ],),
    );
  }

  Widget _sellerSimpleInfo(){
    return Padding(
      padding: const EdgeInsets.all(15.0),
      child: Row(
        children: [
          /*
          ClipRRect(
            borderRadius: BorderRadius.circular(10),
            child: Container(
              width: 50, height: 50, child: Image.asset("assets/images/user.png"),
            ),
          )
           */
          CircleAvatar(
            radius: 25,
            backgroundImage: Image.asset("assets/images/user.png").image,
          ),
          SizedBox(width: 10),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text("판매자1",style: TextStyle(fontWeight: FontWeight.bold,fontSize: 16)),
              Text("제주시 도담동"),
            ],
          ),
          Expanded(child: ManorTemperature(manorTemp: 37.5))
        ],
      ),
    );
  }
  Widget _bodyWidget(){
    return Column(
      children: [
        _makeSliderImage(),
        _sellerSimpleInfo(),
        
      ],
    );
  }

  Widget _bottomBarWidget(){
   return Container(
     width: size.width,
     height: 55,
     color: Colors.red,
   );
  }
  @override
  Widget build(BuildContext context){
    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: _appbarWidget(),
      body: _bodyWidget(),
    );
  }
}