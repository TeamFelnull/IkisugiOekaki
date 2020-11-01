import pyautogui
import time
import keyboard

start = time.time()
while True:
    elapsed_time = time.time() - start
    if keyboard.is_pressed("i"):
        x, y = pyautogui.position()
        print("cricked")
        break
    elif elapsed_time > 10:
        print("nonclicked")
        break
