import androidx.compose.ui.window.ComposeUIViewController
import com.tsaiting.irremotecontrol.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
