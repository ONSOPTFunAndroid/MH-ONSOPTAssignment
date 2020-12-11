##  [1주차]  View와 ViewGroup

---

### 💻필수 과제 [ 2020. 11. 06 ]

<div span=center>
<img src="https://user-images.githubusercontent.com/39720852/101909047-5180e380-3c00-11eb-88e3-4a0110e0c547.PNG" width = "20%" >
<img src="https://user-images.githubusercontent.com/39720852/101909048-52b21080-3c00-11eb-8d0b-f52d5437ec03.PNG" width = "20%" >
<img src="https://user-images.githubusercontent.com/39720852/101909051-52b21080-3c00-11eb-8b7f-4d81e6697eae.PNG" width = "20%" >
</div>

💡 **빈 칸 안내**

```kotlin
if (name.isEmpty() || id.isEmpty() || password.isEmpty()) {
	toast("빈칸없이 모두 작성해주세요:)")
}
```

- **isEmpty** : 빈 칸이 있는지 확인.

💡 **비밀번호 가리기**

```kotlin
<EditText
...
android:hint="비밀번호를 입력하세요:)"
android:inputType="textPassword" />
```

- **hint** : 미리보기 글씨 제공
- **inputType** : 입력 내용 가리기

### 💻 성장 과제1  [ 2020. 11. 06 ]

<div span=center>
<img src="https://user-images.githubusercontent.com/39720852/101912026-8f800680-3c04-11eb-833c-77c2173fd3c4.PNG" width = "20%" >
</div>

💡 **화면 이동 및 자동 입력**

```kotlin
// SignUpActivity
val intent = Intent()

intent.putExtra("Id", id)
intent.putExtra("Pw", password)
setResult(Activity.RESULT_OK,intent)
finish()
```

```kotlin
// SignInActivity
private val REQUEST_CODE_LOGIN_ACTIVITY =1000
...
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_CODE_LOGIN_ACTIVITY){
            if(resultCode== Activity.RESULT_OK){
                val id=data!!.getStringExtra("Id")
                val pw=data!!.getStringExtra("Pw")
                edt_sign_in_id.setText(id)
                edt_sign_in_password.setText(pw)
            }
        }
    }
```

- **requestCode** : activity 구별
- **onActivityResult** : 호출 Activity로부터 결과를 받기 위해 사용
- **putExtra, getStringExtra** : 값 전달
- **setText** : 내용 자동 입력

### 💻 성장 과제2  [ 2020. 11. 06 ]

<div span=center>
<img src="https://user-images.githubusercontent.com/39720852/101912032-90b13380-3c04-11eb-8591-e6fece2c72f2.PNG" width = "20%" >
</div>

💡 **자동 로그인**

```kotlin
// SignInPreference
private const val ID_KEY = "id"

    fun getId(context: Context): String{
        val sharedPreferences = context.getSharedPreferences(ID_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(ID_KEY,"")?:""
    }

    fun setId(context: Context, id: String){
        val sharedPreferences = context.getSharedPreferences(ID_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putString(ID_KEY,id)
            .apply()
    }
```

```kotlin
// SignInActivity
private fun autoSignIn() {
        val id = SignInPreference.getId(this)

        if (id.isNotEmpty()) {
            val intent = Intent(this, HomeActivity::class.java)
            toast("자동로그인 되었습니다:)")

            startActivity(intent)
        }
}
```

```kotlin
// ToastExt
fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
```

- sharedPreferences : id, pw를 기억
- toast : 토스트 메시지 안내( 확장함수로 이용 )



##  [2주차]  RecyclerView

---

### 💻필수 과제 [ 2020. 12. 11 ]

<div span=center>
<img src="https://user-images.githubusercontent.com/39720852/101912829-9d825700-3c05-11eb-8a0e-e047abdcfa67.PNG" width = "20%" >
</div>

💡 **RecyclerView**

```kotlin
data class PortfolioListData(
    val image: Int,
    val title: String,
    val subTitle: String,
    val date: String,
    val contents: String
)
```

```kotlin
// rv_item_portfolio.xml
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>
        <variable
            name="item"
            type="com.example.mh_onsopt_assignment.vo.PortfolioListData" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        ...

        <de.hdodenhof.circleimageview.CircleImageView
            setImage="@{item.image}"
```

```kotlin
class PortfolioAdapter(private val context: Context) : RecyclerView.Adapter<PortfolioViewHolder>() {
    var data = mutableListOf<PortfolioListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val binding:RvItemPortfolioBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rv_item_portfolio,
            parent,
            false
        )

        return PortfolioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        data[position].let {
            holder.bind(it)
        }
        ...
    }

    override fun getItemCount(): Int = data.size
}
```

```kotlin
class PortfolioViewHolder(val binding: RvItemPortfolioBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: PortfolioListData){
        binding.item = item
    }
}
```

```kotlin
// PortfolioFragment
private fun initRvList(){
        portfolioAdapter = PortfolioAdapter(requireContext())

        binding.rvPortfolio.adapter = portfolioAdapter

        portfolioAdapter.data = dummyPortfolioList.getPortfolioList()

        portfolioAdapter.notifyDataSetChanged()
    }
```

- **data class** : RecyclerView Item 내용 정의
- **Adapter** : 데이터를 ViewHolder에 전달
- **ViewHolder** : 데이터를 뷰에 뿌려줌
- Fragment에서 **RecyclerView와 Adapter를 연결.** 
- 데이터 입력(dummyPortfolioList) 후, 갱신(notifyDataSetChanged) 알리기

### 💻성장 과제1 [ 2020. 11. 06 ]

<div span=center>
<img src="https://user-images.githubusercontent.com/39720852/101912826-9ce9c080-3c05-11eb-81a8-7d143197eedc.PNG" width = "20%" >
</div>

💡 **GridLayout**

```kotlin
<androidx.recyclerview.widget.RecyclerView
            app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
            app:spanCount="3"
			...
            tools:listitem="@layout/rv_item_portfolio_2" />
```

- **layoutManager** : 레이아웃 설정(GridLayout이므로 spanCount까지 설정해줍니다.)
- **tools:listitem**을 사용하여 적용된 프리뷰를 편리하게 바로 확인



##  [3주차]  Fragment, ViewPager, BottomNavigation, TabLayout

---

### 💻필수 과제 [ 2020. 11. 06 ]

<div span=center>
<img src="https://user-images.githubusercontent.com/39720852/101915031-695c6580-3c08-11eb-9b89-32c8dee94e55.PNG" width = "20%" >
<img src="https://user-images.githubusercontent.com/39720852/101915036-6a8d9280-3c08-11eb-873b-620c9c533f5b.PNG" width = "20%" >
</div>

💡 **ViewPager 및 BottomNavigation**

```kotlin
class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var fragments = listOf<Fragment>()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}
```

```kotlin
private lateinit var viewpagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewpagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpagerAdapter.fragments = listOf(
            ProfileFragment(),
            PortfolioFragment(),
            SettingFragment()
        )

        vp_home.adapter = viewpagerAdapter
        
        vp_home.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                bottom_navi.menu.getItem(position).isChecked = true
            }
        })

        bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId){
                R.id.menu_profile -> index = 0
                R.id.menu_portfolio -> index = 1
                R.id.menu_setting -> index = 2
            }
            vp_home.currentItem = index
            true

        }
        ...
```

- **ViewPagerAdapter** : Fragment 연결
- **addOnPageChangeListener** : 화면전환을 감지
- **setOnNavigationItemSelectedListener**: 각 탭 클릭 시, 이벤트 처리

💡 **TabLayout**

```kotlin
rootView.tl_profile.setupWithViewPager(rootView.vp_profile)
        rootView.tl_profile.apply {
            getTabAt(0)?.text = "첫 번째"
            getTabAt(1)?.text = "두 번째"
        }
```

- 위와 동일하게 ViewPager 구현 후, TabLayout과 연결
- **getTabAt(index)?.text** : 인텍스와 일치하는 탭 아이템의 title 입력



##  [6주차]  Server

---

### 💻필수 과제 [ 2020. 12. 11 ]

<div span=center>
<img src="https://user-images.githubusercontent.com/39720852/101917045-1a63ff80-3c0b-11eb-898f-878c7eff48e7.PNG" width = "40%" >
<img src="https://user-images.githubusercontent.com/39720852/101917047-1b952c80-3c0b-11eb-9419-1e166b0b82b9.PNG" width = "40%" >
<img src="https://user-images.githubusercontent.com/39720852/101917731-e806d200-3c0b-11eb-8273-253ec16df80f.PNG" width = "30%" >
</div>

💡 **빈 칸 안내**

```kotlin
// Response
data class ResponseSignData(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val email: String,
        val password: String,
        val userName: String
    )
}

// Request
data class RequestSignupData(
    val email : String,
    val password : String,
    val userName : String
)
```

```kotlin
interface SoptService {

    @Headers("Content-Type: application/json")
    @POST("users/signup")
    fun postSignUp(
        @Body body : RequestSignupData
    ) : Call<ResponseSignData>
    ...
```

```kotlin
object SoptServiceImpl {
    private const val BASE_URL = "http://15.164.83.210:3000/"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : SoptService = retrofit.create(SoptService::class.java)
}
```

```kotlin
private fun postSignUp(email:String, password:String, name:String){
        val call : Call<ResponseSignData> = SoptServiceImpl.service.postSignUp(
            RequestSignupData(email = email,password = password, userName = name)
        )
        call.enqueue(object : Callback<ResponseSignData> {
            override fun onFailure(call: Call<ResponseSignData>, t: Throwable) {
            // 통신 실패 로직
                Log.d("명","실패")
            }
            override fun onResponse(
                call: Call<ResponseSignData>,
                response: Response<ResponseSignData>
            ) {
                response.takeIf { it.isSuccessful}
                    ?.body()
                    ?.let { it ->
                        val intent = Intent()

                        intent.putExtra("Id", email)
                        intent.putExtra("Pw", password)

                        setResult(Activity.RESULT_OK,intent)
                        finish()
                    } ?: showError(response.errorBody())
            }
        })
    }

private fun showError(error : ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        toast(ob.getString("message"))
    }
```

- **data class** : 통신을 위한 객체 정의
- **SoptService** : 식별 URL을 interface로 설계
- **SoptServiceImpl** : base_url이 있는 실제 구현체 생성
- showError : error message를 Toast message로 보여줌(ex: 이미 존재하는 이메일 입니다.)
- SoptServiceImpl를 이용하여 Call 객체를 받아 온 후, enqueue를 호출하여 통신을 비동기적으로 요청.
  CallBack 익명클래스를 선언하여 통신이 실패/성공한 경우를 나눠 구현.

