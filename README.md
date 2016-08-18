### 作用
该库的作用是替换默认的系统控件，其中系统控件具体实现部分，可以自己写，也可以使用其它开源库的好看控件，最好基础自系统控件

### 使用方法

1.把`core``widget``utils`三个包拷贝到自己的项目目录下，同时修改`core`包中的`PichuInflaterFactory.PICHU_PATH`为对应`widget`    所在的包名。然后根据需要，添加或者修改对应的控件即可。

2.然后在自己项目的BaseActivity中，setContentView之前，调用`Pichu.init(getLayoutInflater())` 就可以了。如果没有`BaseActivity`，也可以在需要修改的`Acitivity`写在相同的位置，当然最好写一个`BaseActivity`，如下
    
    ``` 
    public class BaseActivity extends Activity{
         @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Pichu.init(getLayoutInflater());
            setContentView(R.layout.activity_layout);
        }
    }
    ```

写这个的原因主要是看了Inflater的源码，以及以前接触到的一个[换肤方案](https://github.com/fengjundev/Android-Skin-Loader)(非常感谢)，发现可以替换系统控件，所以就写了这个库。