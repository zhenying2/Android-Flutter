import 'package:carrot_market/page/detail.dart';
import 'package:carrot_market/repository/contents_repository.dart';
import 'package:carrot_market/utils/data_utils.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:intl/intl.dart';

class Home extends StatefulWidget{
  Home({Key? key}) : super(key: key);

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home>{
  late String currentLocation;
  late ContentsRepository contentsRepository;
  final Map<String,String> locationTypeToString={
    "ara":"아라동", "ora":"오라동","donam":"도남동",
  };

  @override
  void initState(){
    super.initState();
    currentLocation="ara";
    contentsRepository=ContentsRepository();
  }

  PreferredSizeWidget _appbarWidget() {
    return AppBar(
      title: GestureDetector(
        onTap: () {
          print("click");
        },
        child:PopupMenuButton<String>(
          offset: Offset(0,25),
          shape: ShapeBorder.lerp(
            RoundedRectangleBorder(borderRadius: BorderRadius.circular(10.0)),
            RoundedRectangleBorder(borderRadius: BorderRadius.circular(10.0)),
            1),
          onSelected: (String where){
            setState(() {
              currentLocation=where;
            });
          },
          itemBuilder: (BuildContext context){
            return [
              PopupMenuItem(value: "ara",child: Text("아라동")),
              PopupMenuItem(value: "ora",child: Text("오라동")),
              PopupMenuItem(value: "donam",child: Text("도남동")),
            ];
          },
          child: Row(
            children: [
              Text(locationTypeToString[currentLocation]!),
              Icon(Icons.arrow_drop_down),
            ],
          ),
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

  _loadContents(){
    return contentsRepository.loadContentsFromLocation(currentLocation);
  }

  _makeDataList(List<Map<String,String>> datas){
    return ListView.separated(
      padding: const EdgeInsets.symmetric(horizontal: 10),
      itemBuilder: (BuildContext _context, int index){
        return GestureDetector(
          onTap: (){
            Navigator.push(context,
                MaterialPageRoute(builder:(BuildContext context){
              return DetailContentView(
                  data: datas[index],
              );
            }));
            print(datas[index]["title"]);
          },
          child: Container(
            padding: const EdgeInsets.symmetric(vertical: 10),
            child: Row(
              children: [
                ClipRRect(
                  borderRadius:BorderRadius.all(Radius.circular(10)),
                  child: Hero(
                    tag: datas[index]["cid"]!,
                    child: Image.asset(
                      datas[index]["image"]!,
                      width: 100,
                      height: 100,
                    ),
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
                        Text(DataUtils.calcStringToWon(datas[index]["price"]!),style: TextStyle(fontWeight: FontWeight.w500),),
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
          ),
        );
      },
      separatorBuilder: (BuildContext _context, int index){
        return Container(height: 1, color: Colors.black.withOpacity(0.4));
      },
      itemCount: 10,
    );
  }

  Widget _bodyWidget(){
    return FutureBuilder(
      future: _loadContents(),
      builder: (BuildContext context,dynamic snapshot) {
        if (snapshot.connectionState != ConnectionState.done) {
          return Center(child: CircularProgressIndicator());
        }
        if (snapshot.hasError) {
          return Center(child: Text("데이터 오류"));
        }
        if (snapshot.hasData) {
          return _makeDataList(snapshot.data);
        }
        return Center(child: Text("해당 지역에 데이터 없습니다."));
      });
  }

  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: _appbarWidget(),
      body: _bodyWidget(),
    );
  }
}
