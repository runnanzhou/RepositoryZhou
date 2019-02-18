# Runnan Zhou, Student, CSc 110, spring 2018, Section n
# Programming Assignment #7, 03/20/2018
# This function is to judge whether the state is gerrymandered.
from DrawingPanel import *
#define 2 constants of width and length of the panel.
WIDTH=500
LENGTH=500
def intro():
    # print the introduction.
    print("This program allows you to search through")
    print("data about congresssional voting districts")
    print("and determine whether a particular state is")
    print("gerrymandered.")
    print("")

def find(docu,x,y):
    '''
    define the function to find the state and compute the result.
    :param docu: the file
    :param x: the width of panel
    :param y: the length of anel
    :return:
    '''
    state=input("Which state do you want to look up? ").lower()
    file = open(docu)
    doc = file.readlines()
    found=False
    sum_demo=0
    sum_repu=0
    demo_wasted=0
    repu_wasted=0
    gerry=0
    for line in doc:
        line=line.lower()
        line=line.split(",")
        if state == line[0]:
            found=True
            panel = DrawingPanel(x, y)  #draw the panel in the size given by the user
            panel.set_background("white")
            panel.draw_line(0, 20, x, 20) # draw the lines which are required.
            panel.draw_line(x / 2, 0, x / 2, y)
            num = (len(line) - 1) // 3 # calculate the group.
            for i in range(0,num): # Calculate the data which are used to compute the
                demo_vote=int(line[3*i+2])
                repu_vote=int(line[3*i+3])
                w = int(demo_vote / (demo_vote + repu_vote) * x)
                panel.fill_rect(0, 25 * (i+1), w, 20,"blue")
                panel.fill_rect(w,25*(i+1),x-w,20,"red")
                if demo_vote>repu_vote :
                    demo_wasted=demo_vote-(repu_vote+demo_vote)//2-1
                    repu_wasted=repu_vote
                elif demo_vote<repu_vote:
                    demo_wasted=demo_vote
                    repu_wasted=repu_vote-(repu_vote+demo_vote)//2-1
                sum_demo+=demo_wasted
                sum_repu+=repu_wasted
            print("Total Wasted Democratic votes:", sum_demo)
            print("Total Wasted Republican votes:", sum_repu)

            ifgerry(sum_demo,sum_repu)
            b = voters(state, "eligible_voters")
            panel.draw_string(state.capitalize(), 0, 0)
            panel.draw_string(str(b) + " " + "eligible voters", x - 120, 0)

    if found == False: # show whether the state is found.
        print("\""+state+"\"","not found.")

def ifgerry(sum_demo,sum_repu):
    '''
    define the function to calculate the result to show whether it is gerrymandered.
    :param sum_demo: the sum of wasted vote in demo party
    :param sum_repu: the sum of wasted vote in repu party
    :return:
    '''
    if sum_demo > sum_repu:
                gerry = (sum_demo - sum_repu) / (sum_repu + sum_demo)
                if gerry >= 0.07:
                    print("Gerrymandered benefiting the Republicans")

    else:
                gerry = (sum_repu - sum_demo) / (sum_demo + sum_repu)
                if gerry >= 0.07:
                    print("Gerrymandered benefiting the Democrats")
def voters(state,docu):
    '''define a function to find out the data.

    :param state: the input of user
    :param docu: file
    :return:
    '''
    file=open(docu)
    doc= file.readlines()
    for line in doc:
        line=line.lower()
        line=line.split(",")
        if state == line[0]:
            print(line[1].replace("\n",""),"eligible voters")
            a=line[1].replace("\n","")
            return a

def main():
    intro()
    find("districts.txt",500,500)

main()
