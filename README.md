## 作用
该库的作用主要是，在不修改xml文件的情况下,可以快速替换默认的系统控件，其中自定义的控件具体实现部分，可以自己写，也可以使用其它开源库的好看控件，最好继承自系统控件。
举例：
main_layout.xml: 注意Button的颜色，是默认的粉色
    
    
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout
        android:id="@+id/root_ll"
        android:orientation="vertical"
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        tools:context="com.dove.flyer.pichu.MainActivity">
        
        <Button
            android:background="@color/colorAccent"
            android:id="@+id/test_btn"
            android:layout_width="match_parent"
            android:layout_height="40dp"/>
    
    </LinearLayout>


在`MainActivity`中，不做任何操作，代码如下

    
     @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_layout);
            mButton = (Button) findViewById(R.id.test_btn);
            mRootLl = (LinearLayout) findViewById(R.id.root_ll);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                }
            });
        }
    

显示效果如图 ![alt](https://github.com/flyer88/Pichu/blob/master/MainActivity.png)

然后对本项目的`Widget`包下的`Button`进行如下修改

    
    public class Button extends android.widget.Button{
        public Button(Context context) {
            super(context);
        }

        public Button(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public Button(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init(){
            setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

        
    }
    

在`SecondActivity`中，写入如下代码

    
        @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Pichu.init(getLayoutInflater());
                setContentView(R.layout.activity_layout);
                findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
    

最终在`SecondActivity`中的显示如图 ![alt](https://github.com/flyer88/Pichu/blob/master/SecondActivity.png)


注意，从头至尾，不需要对`xml`进行任何修改，唯一需要做的是，实现一个自己想要的`Button`，但效果确实替换了系统的`Button`


=======================================================================================================================



## 使用方法

1.把`core``widget``utils`三个包拷贝到自己的项目目录下，同时修改`core`包中的`PichuInflaterFactory.PICHU_PATH`为对应`widget`    所在的包名。然后根据需要，添加或者修改对应的控件即可。

2.然后在自己项目的BaseActivity中，setContentView之前，调用`Pichu.init(getLayoutInflater())` 就可以了。如果没有`BaseActivity`，也可以在需要修改的`Acitivity`写在相同的位置，当然最好写一个`BaseActivity`，如下
    
    
    public class BaseActivity extends Activity{
         @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Pichu.init(getLayoutInflater());
            setContentView(R.layout.activity_layout);
        }
    }
    
## 最后

写这个的原因主要是看了Inflater的源码，以及以前接触到的一个[换肤方案](https://github.com/fengjundev/Android-Skin-Loader)(非常感谢)，发现可以替换系统控件，所以就写了这个。
