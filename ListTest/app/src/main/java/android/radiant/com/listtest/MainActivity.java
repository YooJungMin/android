package android.radiant.com.listtest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.DimenRes;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.OverScroller;
import android.widget.Scroller;
import android.widget.Toast;

public class MainActivity extends Activity {
    boolean firstDragFlag = true;
    boolean dragFlag = false;
    boolean  motionFlag = false;
    float startYPosition = 0;
    float endYPosition = 0;
//    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ArrayAdapter를 통해 LIST로 표시할 오브젝트를 지정한다.
        // 여기서는 심플하게 그냥 String
        // 레이아웃 android.R.layout.simple_list_item_1 는 안드로이드가 기본적으로 제공하는 간단한 아이템 레이아웃
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        // 아이템을 추가
        adapter.add("item1");
        adapter.add("item2");
        adapter.add("item3");
        adapter.add("item4");
        adapter.add("item5");
        adapter.add("item6");
        adapter.add("item7");
        adapter.add("item8");
        adapter.add("item9");
        adapter.add("item10");
        adapter.add("item11");
        adapter.add("item12");
        adapter.add("item13");
        adapter.add("item14");
        adapter.add("item15");
        adapter.add("item16");
        adapter.add("item17");
        adapter.add("item18");
        adapter.add("item19");
        adapter.add("item20");
        adapter.add("item21");
        adapter.add("item22");
        adapter.add("item23");
        adapter.add("item24");
        adapter.add("item25");


        // ListView 가져오기
        final ListView listView = (ListView) findViewById(R.id.listview);
        // ListView에 각각의 아이템표시를 제어하는 Adapter를 설정
        listView.setAdapter(adapter);

        // 아이템을 [클릭]시의 이벤트 리스너를 등록
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                // TODO 아이템 클릭시에 구현할 내용은 여기에.
                String item = (String) listView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
            }
        });

        // 아이템을 [선택]시의 이벤트 리스너를 등록
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                ListView listView = (ListView) parent;
                // TODO 아이템 선택시에 구현할 내용은 여기에.
                String item = (String) listView.getSelectedItem();
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

      /*  listView.setOverscrollFooter(getDrawable(R.id));
        listView.setOverscrollHeader(drawable);
        listView.setBackgroundColor(Color.LTGRAY);
        listView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        listView.setScrollContainer(false);*/








/*
        findViewById(R.id.listview).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_SCROLL:
                        Log.d("ACTION", "SCROLL");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("ACTION", "pointerDOWN");
                        break;
                }
                return false;
            }
        });*/




/*


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                                         @Override
                                         public void onScroll(AbsListView view, int firstVisibleItem,
                                                              int visibleItemCount, int totalItemCount) {


                                             if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                                                 Log.d("ddd", "최하");
                                             }

                                             if (firstVisibleItem == 0 && view.getChildAt(0) != null && view.getChildAt(0).getTop() == 0) {
                                                 Log.d("ddd", "최상");

                                             }
                                         }

                                         @Override
                                         public void onScrollStateChanged(AbsListView view, int scrollState) {
                                             if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && view.getLastVisiblePosition() == view.getAdapter().getCount() - 1
                                                     && view.getChildAt(view.getChildCount() - 1).getBottom() <= view.getHeight()) {

                                                 view.setSelection(view.getAdapter().getCount() - 1);
                                             }

                                         }


                                     }

        );





            listView.setOnTouchListener(new View.OnTouchListener()

            {
                @Override
                public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:       //터치를 한 후 움직이고 있으면
                        dragFlag = true;
                        if (firstDragFlag) {     //터치후 계속 드래그 하고 있다면 ACTION_MOVE가 계속 일어날 것임으로 무브를 시작한 첫번째 터치만 값을 저장함
                            startYPosition = event.getY(); //첫번째 터치의 Y(높이)를 저장
                            firstDragFlag = false;   //두번째 MOVE가 실행되지 못하도록 플래그 변경
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        endYPosition = event.getY();
                        firstDragFlag = true;

                        if (dragFlag) {  //드래그를 하다가 터치를 실행
                            // 시작Y가 끝 Y보다 크다면 터치가 아래서 위로 이루어졌다는 것이고, 스크롤은 아래로내려갔다는 뜻이다.
                            // (startYPosition - endYPosition) > 10 은 터치로 이동한 거리가 10픽셀 이상은 이동해야 스크롤 이동으로 감지하겠다는 뜻임으로 필요하지 않으면 제거해도 된다.

                            if ((startYPosition > endYPosition) && (startYPosition - endYPosition) > 1) {
                                // Log.d("ddd", "위로");

                            }
                            //시작 Y가 끝 보다 작다면 터치가 위에서 아래로 이러우졌다는 것이고, 스크롤이 올라갔다는 뜻이다.

                            else if ((startYPosition < endYPosition) && (endYPosition - startYPosition) > 1) {
                                View c = listView.getChildAt(0);
                                int scrolly = -c.getTop() + listView.getFirstVisiblePosition() * c.getHeight();
                                if (scrolly < 100) {
                                    //Log.d("ddd", "아래로");
                                    listView.setSelection(listView.getAdapter().getCount() - listView.getAdapter().getCount()   );
                                }
                            }
                        }

                        startYPosition = 0.0f;
                        endYPosition = 0.0f;
                        motionFlag = false;
                        break;
                }
                return false;
            }
            }

            );

*/
        }




    }