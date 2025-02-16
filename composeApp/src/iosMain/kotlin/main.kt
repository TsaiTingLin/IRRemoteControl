import androidx.compose.ui.window.ComposeUIViewController
import com.tsaiting.irremotecontrol.view.MainScreen
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { MainScreen() }