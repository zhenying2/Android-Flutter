import 'package:carrot_market/page/home.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

class App extends StatefulWidget {
  App({Key? key}) : super(key: key);

  @override
  _AppState createState() => _AppState();
}

class _AppState extends State<App> {
  late int _currentPageIndex;

  @override
  void initState(){
    super.initState();
    _currentPageIndex=0;
  }

  Widget _bodyWidget(){
    switch (_currentPageIndex){
      case 0:
        return Home();
        break;
      case 1:
        return Container();
        break;
      case 2:
        return Container();
        break;
      case 3:
        return Container();
        break;
      case 4:
        return Container();
        break;
    }
    return Container();
  }

  BottomNavigationBarItem _bottomNavigationBarItem(String icon, String label){
    return BottomNavigationBarItem(
      icon: Padding(
        padding:const EdgeInsets.only(bottom: 5),
        child:SvgPicture.asset("assets/svg/${icon}_off.svg",width: 22),
      ),
      activeIcon: Padding(
        padding:const EdgeInsets.only(bottom: 5),
        child:SvgPicture.asset("assets/svg/${icon}_on.svg",width: 22),
      ),
      label: label,
    );
  }

  Widget _bottomNavigationBarWidget(){
    return BottomNavigationBar(
      type: BottomNavigationBarType.fixed,
      onTap: (int index){
        print(index);
        setState(() {
          _currentPageIndex=index;
        });
      },
      currentIndex: _currentPageIndex,
      selectedFontSize: 12,
      selectedItemColor: Colors.black,
      selectedLabelStyle: TextStyle(color: Colors.black),
      items: [
        _bottomNavigationBarItem("home", "홈"),
        _bottomNavigationBarItem("notes", "동네생활"),
        _bottomNavigationBarItem("location", "내 근처"),
        _bottomNavigationBarItem("chat", "채팅"),
        _bottomNavigationBarItem("user", "나의 당근")
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //appBar: _appbarWidget(),
      body: _bodyWidget(),
      bottomNavigationBar: _bottomNavigationBarWidget(),
    );
  }
}
