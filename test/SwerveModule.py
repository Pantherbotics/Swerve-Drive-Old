import math
import tkinter
import tkinter as tk
from tkinter import messagebox
tkinter.messagebox

class SwerveModule:
    def __init__(self, isReversed):
        self.isReversed = isReversed
        self.steeringAngle = 0.0
        self.steeringMaxSpeed = 1.5*(2*math.pi) #2pi rad /s
        self.steeringSetpoint = 0.0
        self.driveSpeed = 0.0
        self.kP = 0.7

    def update(self, dt = 0.01):
        output = self.kP * self.getModifiedError()
        output = self.clamp(-1.0, 1.0, output)
        self.steeringAngle += output * self.steeringMaxSpeed * dt
        if self.steeringAngle < -math.pi:
            steeringAngle = 2*math.pi + self.steeringAngle
        if self.steeringAngle > math.pi:
            steeringAngle = steeringAngle - 2*math.pi

    def getError(self):
        return self.steeringSetpoint - self.steeringAngle
    
    def getModifiedError(self):
        return self.boundHalfRadians(self.getError())

    def boundHalfRadians(self, radians):
        while radians >= math.pi:
            radians -=2*math.pi
        while radians < -math.pi: 
            radians +=2*math.pi
        return radians

    def clamp(self, min, max, val):
        if val < min:
            return min
        if val > max:
            return max
        else:
            return val

module = SwerveModule(False)
module.steeringSetpoint = math.pi+0.2

count = 0
while abs(module.getModifiedError()) > 0.0001:
    position = module.steeringAngle
    print(position)
    print(module.getModifiedError())
    module.update()
    count+=1

print(count * 0.01, "seconds elapsed")


#START OF JAKE'S Annoying GUI

root = tkinter.Tk()

def Test1():
   messagebox.showinfo( "Hello From Python", "HI")

frame1 = tk.Frame(root, width=120, height=120, background="red")
frame2 = tk.Frame(root, width=110, height = 110, background="orange")
frame3 = tk.Frame(root, width=100, height = 100, background="yellow")
frame4 = tk.Frame(root, width=90, height = 90, background="green")
frame5 = tk.Frame(root, width=80, height = 80, background="blue")
frame6 = tk.Frame(root, width=70, height = 70, background="purple")
frame7 = tk.Frame(root, width=60, height = 60, background="red")
frame8 = tk.Frame(root, width=50, height = 50, background="orange")
frame9 = tk.Frame(root, width=40, height = 40, background="yellow")
frame10 = tk.Frame(root, width=30, height = 30, background="green")
frame11 = tk.Frame(root, width=20, height = 20, background="blue")
frame12 = tk.Frame(root, width=10, height = 10, background="purple")
frame13 = tk.Button(root, width=1, height=2, background="purple", command = Test1)

frame1.pack(fill=None, expand=True)
frame2.pack(fill=None, expand=False)
frame3.pack(fill=None, expand=False)
frame4.pack(fill=None, expand=False)
frame5.pack(fill=None, expand=False)
frame6.pack(fill=None, expand=False)
frame7.pack(fill=None, expand=False)
frame8.pack(fill=None, expand=False)
frame9.pack(fill=None, expand=False)
frame10.pack(fill=None, expand=False)
frame11.pack(fill=None, expand=False)
frame12.pack(fill=None, expand=False)
frame13.pack(fill=None, expand=False)

#frame2.place(relx=0.5, rely=0.5, anchor="c")


root.mainloop()