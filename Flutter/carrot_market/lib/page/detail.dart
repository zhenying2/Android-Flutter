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

  Widget _line(){
    return Container(
      margin: const EdgeInsets.symmetric(horizontal: 15),
      height: 1,
      color: Colors.grey.withOpacity(0.3),
    );
  }

  Widget _contentDetail(){
    return Container(
      margin: const EdgeInsets.symmetric(horizontal: 15),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          SizedBox(height: 20),
          Text(widget.data["title"]!,style: TextStyle(fontWeight: FontWeight.bold,fontSize: 20)),
          Text("디지털/가전 * 22시간 전",style: TextStyle(color: Colors.grey,fontSize: 12)),
          SizedBox(height: 15),
          Text("선물받은 새 상품이고\n상품꺼내보기만 했습니다.\n거래는 직거래만 합니다.",style: TextStyle(height: 1.5,fontSize: 15)),
          SizedBox(height: 15),
          Text("채팅 3 * 관심 17 * 조회 295",style: TextStyle(color: Colors.grey,fontSize: 12)),
          SizedBox(height: 15),
      ],),
    );
  }

  Widget _otherCellContents(){
    return Padding(
      padding: const EdgeInsets.all(15),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text("판매자님의 판매 상품",style: TextStyle(fontSize: 15,fontWeight: FontWeight.bold)),
          Text("모두보기",style: TextStyle(fontSize: 12,fontWeight: FontWeight.bold)),
        ],
      ),
    );
  }

  Widget _bodyWidget(){
    return CustomScrollView(
      slivers: [
        SliverList(delegate: SliverChildListDelegate(
            [
              _makeSliderImage(),
              _sellerSimpleInfo(),
              _line(),
              _contentDetail(),
              _line(),
              _otherCellContents(),
            ]
          ),
        ),
        SliverPadding(
          padding: const EdgeInsets.symmetric(horizontal: 15),
          sliver: SliverGrid(
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2,mainAxisSpacing: 10,crossAxisSpacing: 10),
            delegate: SliverChildListDelegate(List.generate(20, (index){
              return Container(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: [
                    ClipRRect(
                      borderRadius:BorderRadius.circular(10),
                      child: Container(
                          color: Colors.grey,
                          height: 120,
                        ),
                    ),
                    Text("상품 제목",style: TextStyle(fontSize: 14)),
                    Text("금액",style: TextStyle(fontSize: 14,fontWeight: FontWeight.bold)),
                  ],
                ),
              );
            }).toList()),
          ),
        ),
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