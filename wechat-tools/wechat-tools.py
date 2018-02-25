from wxpy import *
import time
import datetime

bot = Bot(cache_path=True)

friend_name = input("请输入好友名称:")

if friend_name == "王欢":
    print("请不要骚扰开发者! ")
    print("程序将在5秒钟后退出")
    time.sleep(5)
    exit()

my_friend = bot.friends().search(friend_name)[0]

message = input("请输入需要发送的消息:")

send_type = input("请输入发送类型(1为定时发送,2为每隔时间段循环发送):")


# 定时发送消息
def regularly_send():
    send_time = input("请输入发送时间(格式为yyyy-mm-dd HH:MM):")

    while True:
        time.sleep(5)
        now = datetime.datetime.now().strftime("%Y-%m-%d %H:%M")
        print("当前时间:" + now)
        print("目标时间:" + send_time)
        if send_time == now:
            print("发送消息:" + message)
            my_friend.send(message)
            print("本次任务完成 程序退出")
            time.sleep(3)
            exit()


# 循环发送消息
def cycle_send():
    cycle_ranger = input("请输入发送间隔时间(单位为秒):")
    while True:
        time.sleep(int(cycle_ranger))
        print("发送消息:" + message)
        my_friend.send(message)


functions = {'1': regularly_send, '2': cycle_send}

func = functions[send_type]

func()
