import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

//할일 클래스
class Todo{
  bool isDone=false;
  String title;
  Todo(this.title);
}

//시작 클래스
class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '할 일 관리',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: TodoListPage(),
    );
  }
}

//TodoListPage 클래스
class TodoListPage extends StatefulWidget{
  @override
  _TodoListPageState createState() => _TodoListPageState();
}

//TodoListPage의 State 클래스
class _TodoListPageState extends State<TodoListPage>{
  //할 일 목록을 저장할 리스트
  final _items=<Todo>[];

  //할일 문자열 조작을 위한 컨트롤러
  var _todoController=TextEditingController();

  @override
  void dispose(){
    _todoController.dispose(); //사용이 끝나면 해제
    super.dispose();
  }

  //할일 추가 메서드
  void _addTodo(Todo todo){
    setState(() {
      _items.add(todo);
      _todoController.text=""; //할일 입력필드를 비움
    });
  }

  //할일 삭제 메서드
  void _deleteTodo(Todo todo){
    setState(() {
      _items.remove(todo);
    });
  }

  //할일 완료/미완료 메서드
  void _toggleTodo(Todo todo){
    setState(() {
      todo.isDone= !todo.isDone;
    });
  }

  //할 일 객체를 ListTile 형태로 변경하는 메서드
  Widget _buildItemWidget(Todo todo){
    return ListTile(
      onTap: ()=> _toggleTodo(todo),
      title: Text(
        todo.title, //할일
        style: todo.isDone
        ? TextStyle(decoration: TextDecoration.lineThrough, fontStyle: FontStyle.italic) : null,
      ),
    trailing: IconButton(
    icon: Icon(Icons.delete_forever),
    onPressed: () => _deleteTodo(todo),
    ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('남은 할 일'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: <Widget>[
            Row(
              children: <Widget>[
                Expanded(
                    child: TextField(
                      controller: _todoController,
                    ),
                ),
                ElevatedButton(
                    onPressed: () => _addTodo(Todo(_todoController.text)),
                    child: Text('추가'),
                ),
              ],
            ),
            Expanded(
              child: ListView(
                children: _items.map((todo) => _buildItemWidget(todo)).toList(),
              ),
            )
          ],
        ),
      )
    );
  }
}
