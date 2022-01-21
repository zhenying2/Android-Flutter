import 'package:flutter/material.dart';
import 'package:jinplex/screen/home_screen.dart';
import 'package:jinplex/screen/more_screen.dart';
import 'package:jinplex/widget/bottom_bar.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget{
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp>{
  late TabController controller;
  @override
  Widget build(BuildContext context){
    return MaterialApp(
      title: 'Jinplex',
      theme: ThemeData(
        brightness: Brightness.dark,
        primaryColor: Colors.black,
        accentColor: Colors.white,
      ),
      home: DefaultTabController(
        length: 4,
        child:Scaffold(
          body: TabBarView(
            physics: NeverScrollableScrollPhysics(),
            children: [
              HomeScreen(),
              Container(child: Center(child: Text('search'),),),
              Container(child: Center(child: Text('save'),),),
              MoreScreen(),
            ],
          ),
          bottomNavigationBar: Bottom(),
        ),
      ),
    );
  }
}
