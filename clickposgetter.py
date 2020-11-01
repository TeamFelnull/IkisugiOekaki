import pyautogui
import ctypes

while True:
    if ctypes.windll.user32.GetAsyncKeyState(0x01) == 0x8000:
        x, y = pyautogui.position()
        print(str(x) + ':' + str(y))
        break