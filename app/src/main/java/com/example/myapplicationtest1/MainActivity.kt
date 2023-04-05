package com.example.myapplicationtest1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationtest1.ui.theme.MyApplicationTest1Theme
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTest1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(/*"Android"*/)
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String) {
//    val text = remember { mutableStateOf("") }
//    val text1 = remember { mutableStateOf("") }
//    val text2 = remember { mutableStateOf("") }
//
//    Button(onClick = {
//        text.value = "${text.value}1${text.value}"
//        text1.value = "${text1.value}1${text1.value}"
//    }) {
//        Text(text = text.value)
//        Log.d("my_log"," 2")
//    }
//    Log.d("my_log","1")
//    Column() {
//        Text(text = text1.value)
//    }
//}

@Composable
fun Greeting() {

    val state = remember {
        mutableStateOf("")
    }

    val action = remember {
        mutableStateOf("")
    }

    Log.d("my_log","root")

    ActionsHandler(actions = action)

    LoginScreen(state, action)

//    key(action.value) {
//        when (action.value) {
//            "" -> Log.d("my_log","empty string")
//            " " -> Log.d("my_log","1")
//            "  " -> Log.d("my_log","2")
//            else -> Log.d("my_log","else")
//        }
//    }
}

@Composable
fun ActionsHandler(actions: State<String>) {
    when (actions.value) {
        "" -> Log.d("my_log","empty string")
        " " -> Log.d("my_log","1")
        "  " -> Log.d("my_log","2")
        else -> Log.d("my_log","else")
    }
}

@Composable
private fun LoginScreen(
    state: MutableState<String>,
    action: MutableState<String>,
    onEventSend: () -> Unit = {}
) {
    Log.d("my_log","root 1")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Log.d("my_log","root 2")
        LoginBox(
            state = state,
            action = action,
            onEventSend = onEventSend
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginBox(
    state: MutableState<String>,
    action: MutableState<String>,
    onEventSend: () -> Unit,
) {
    Log.d("my_log","root 3")
    Box(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Blue),
    ) {

        Log.d("my_log","root 4")
        Inner(state = state, action = action)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inner(state: MutableState<String>,
          action: MutableState<String>) {
    Column(
        modifier = Modifier
            .padding(top = 40.dp, end = 20.dp, bottom = 25.dp)
//                .background(Theme.colors.primaryBackground)
            .wrapContentSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
//            if (state.value.isSending) LoadingBar()
        Log.d("my_log","root 5")
        Text(
            modifier = Modifier
                .padding(start = 20.dp),
            text = "Profit Land ",
//                color = Theme.colors.headerTextColor,
//                fontFamily = FontFamily.Serif,
            fontSize = 36.sp
        )

        Spacer(modifier = Modifier.height(55.dp))

        TextField(modifier = Modifier
            .padding(start = 20.dp),
            value = state.value, onValueChange = {state.value = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next,
            ))

        Spacer(modifier = Modifier.height(43.dp))

//            Spacer(modifier = Modifier.height(13.dp))

        val isChecked = remember {
            mutableStateOf(true)
        }

        Row(
            modifier = Modifier
                .padding(start = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                },
            )

            Text(text = "Запомнить меня")
        }

        Button(
            modifier = Modifier
                .padding(start = 20.dp)
                .height(50.dp)
                .fillMaxWidth(),
            onClick = { action.value = action.value+ " " }
        ) {
            Text(text = "Button")
            Log.d("my_log","root 6")
        }

        Row(
            modifier = Modifier
                .padding(top = 11.dp, start = 20.dp)
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.Center
        ) {
            ClickableText(
                text = AnnotatedString("some text"),
                style = MaterialTheme.typography.bodyMedium.plus(TextStyle(color = MaterialTheme.colorScheme.primary)),
                onClick = {  }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTest1Theme {
        Greeting(/*"Android"*/)
    }
}