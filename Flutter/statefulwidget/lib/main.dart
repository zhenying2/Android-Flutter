import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage>{
  var _ischecked=false;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(

          title: Text('checkbox/radio/switch'),
        ),
        body: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Checkbox(
                  value: _ischecked,
                  onChanged: (value) {
                    setState(() {
                      _ischecked=value!;
                    });},
                ),
                SizedBox(height: 40,),
                Switch(
                    value: _ischecked,
                    onChanged: (value) {
                    setState(() {
                    _ischecked=value;
                    });})
              ],
            ),
          ),
        )
    );
  }
}
