import tkinter
import tkinter as tk
from tkinter import messagebox
tkinter.messagebox

root = tkinter.Tk(className=" Gay Roblox Hack XD")
root.resizable(0, 0)
root.geometry('600x400')

def TPToBase():
   messagebox.showinfo("Success!", "You have been Tp'd!")

def Speed():
   messagebox.showinfo("Success!", "Your speed have increased!")

def Jump():
   messagebox.showinfo("Success!", "Your jumpheight have increased!")

tpBase = tk.Button(root, width=28, height=2, background="Blue", text="Tp To Base", command = TPToBase)
speed = tk.Button(root, width=28, height=2, background="Blue", text="Speed 100", command = Speed)
jump = tk.Button(root, width=28, height=2, background="Blue", text="Jump 100", command = Jump)

tpBase.place(relx=0, rely=0, anchor="nw")
speed.place(relx=0.5, rely=0, anchor="n")
jump.place(relx=1, rely=0, anchor="ne")

#####--NOTES--#####
#frame1.place(relx=0.5, rely=0.5, anchor="c")
#frame1.pack(fill=None, expand=False)
#frame1 = tk.Frame(root, width=120, height=120, background="red")
#def Test1():
#   messagebox.showinfo( "Hello From Python", "HI")
#tpBase = tk.Button(root, width=30, height=2, background="Blue", text="Tp To Base", command = TPToBase)

root.mainloop()