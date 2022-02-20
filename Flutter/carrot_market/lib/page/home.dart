import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:intl/intl.dart';

class Home extends StatefulWidget {
  Home({Key? key}) : super(key: key);

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  List<Map<String,String>> datas=[];

  @override
  void initState(){
    super.initState();
    datas=[
      {
        "image" : "assets/images/ara-1.jpg",
        "title" : "제목 1",
        "location" : "위치 1",
        "price" : "10000",
        "likes" : "1"
      },
    {
    "image" : "assets/images/ara-2.jpg",
    "title" : "제목 2",
    "location" : "위치 2",
    "price" : "20000",
    "likes" : "2"
    },
    {
    "image" : "assets/images/ara-3.jpg",
    "title" : "제목 3",
    "location" : "위치 3",
    "price" : "30000",
    "likes" : "3"
    },
    {
    "image" : "assets/images/ara-4.jpg",
    "title" : "제목 4",
    "location" : "위치 4",
    "price" : "40000",
    "likes" : "4"
    },
    {
    "image" : "assets/images/ara-5.jpg",
    "title" : "제목 5",
    "location" : "위치 5",
    "price" : "50000",
    "likes" : "5"
    },
    {
    "image" : "assets/images/ara-6.jpg",
    "title" : "제목 6",
    "location" : "위치 6",
    "price" : "60000",
    "likes" : "6"
    },
    ];
  }
  PreferredSizeWidget _appbarWidget() {
    return AppBar(
      title: GestureDetector(
        onTap: () {
          print("click");
        },
        child: Row(
          children: [
            Text("아라동"),
            Icon(Icons.arrow_drop_down),
          ],
        ),
      ),
      elevation: 1,
      actions: [
        IconButton(onPressed: () {}, icon: Icon(Icons.search)),
        IconButton(onPressed: () {}, icon: Icon(Icons.tune)),
        IconButton(
            onPressed: () {},
            icon: SvgPicture.asset(
              "assets/svg/bell.svg",
              width: 22,
            )),
      ],
    );
  }

  final oCcy=new NumberFormat("#,###","ko_KR");
  String calcStringToWon(String priceString){
    return "${oCcy.format(int.parse(priceString))}원";
  }
  ListView _bodyWidget(){
    return ListView.separated(
      padding: const EdgeInsets.symmetric(horizontal: 10),
      itemBuilder: (BuildContext _context, int index){
          return Container(
            padding: const EdgeInsets.symmetric(vertical: 10),
            child: Row(
              children: [
                ClipRRect(
                  borderRadius:BorderRadius.all(Radius.circular(10)),
                  child: Image.asset(
                    datas[index]["image"]!,
                    width: 100,
                    height: 100,
                  ),
                ),
                Expanded(
                  child:Container(
                    height: 100,
                    padding: const EdgeInsets.only(left: 20),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(datas[index]["title"]!,style: TextStyle(fontSize: 15),overflow: TextOverflow.ellipsis,),
                        SizedBox(height: 5),
                        Text(datas[index]["location"]!,style: TextStyle(fontSize: 12,color: Colors.black.withOpacity(0.3)),),
                        SizedBox(height: 5),
                        Text(datas[index]["price"]!,style: TextStyle(fontWeight: FontWeight.w500),),
                        Expanded(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.end,
                            crossAxisAlignment: CrossAxisAlignment.end,
                            children: [
                              SvgPicture.asset("assets/svg/heart_off.svg",width: 13,height: 13,),
                              SizedBox(width: 5,),
                              Text(datas[index]["likes"]!),
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          );
        },
        separatorBuilder: (BuildContext _context, int index){
          return Container(height: 1, color: Colors.black.withOpacity(0.4));
        },
        itemCount: 10,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: _appbarWidget(),
      body: _bodyWidget(),
      //bottomNavigationBar: Container(),
    );
  }
}
