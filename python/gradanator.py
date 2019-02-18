# Runnan Zhou, Student, CSc 110, spring 2018, Section n
# Programming Assignment #5, 02/27/2018
# This function is to let the user input the score of the exam, homework, and quizzes so that this program can
# calculate the overall score and grade to show the user.
def intro():
    #  introduction of the program
    print("This program reads exam/homework scores")
    print("and reports your overall course grade.")
    print()

def mid_term(n):
    # This function let you input the score of the midterm exam.
    print("Midterm",str(n)+":")
    w=int(input("Weight (0-100)? "))  # input the weight of exam
    s=int(input("Score earned? "))  # input the score of exam.
    f=int(input("Were scores shifted (1=yes, 2=no)? ")) # input the fact whether there is a score shift
    if f== 1: # If there is, execute this if function.
        a=int(input("Shift amount? "))
        s=s+a
        s=min(s,100) # To make the max score become 100.
    print("Total points =",s,"/ 100") # print total score
    print("Weighted score =",round((s/100*w),1),"/",w) # print Weighted score
    print()
    return round((s/100*w),1)

def final():  # This function let you input the score of the final exam.
    print("Final:")
    w = int(input("Weight (0-100)? ")) # input the weight of exam
    s = int(input("Score earned? ")) # input the score of exam.
    f = int(input("Were scores shifted (1=yes, 2=no)? ")) # input the fact whether there is a score shift
    if f== 1:  # If there is, execute this if function.
        a=int(input("Shift amount? "))
        s=s+a
        s=min(s,100) #To make the max score become 100.
    print("Total points =", s, "/ 100")# print total score
    print("Weighted score =", round((s / 100 * w), 1), "/", w)# print Weighted score
    print()
    return round((s / 100 * w), 1)

def homework():  # This function let you input the score of homework.
    c=0
    d=0
    print("Homework:")
    w = int(input("Weight (0-100)? "))
    n=input("Number of assignments? ")
    for i in range (1,int(n)+1): # This for loop is used to let the user input their score at several times.
        ass=int(input("Assignment "+str(i)+" score? "))
        assm=int(input("Assignment "+str(i)+" max? "))
        c+=ass  # This acumulative add is to make the sum of the points you get at homework.
        d+=assm # This acumulative add is to make the sum of the total points you can get.
    att=int(input("How many sections did you attend? "))
    attp=min(att*3,34)
    print("Section points =",attp,"/","34")
    print("Total points =",c+attp,"/",d+34)
    print("Weighted score =", round(((attp+c) / (d+34) * w), 1), "/", w)
    print()
    return round(((attp+c) / (d+34) * w), 1)

def quizzes(): # This function is to make it possible to calculate the quizzes score.
    print("Quizzes:")
    w = int(input("Weight (0-100)? "))
    p = int(input("Total points earned? "))
    t=int(input("Total points possible? "))
    print("Total points =",p,"/",t)
    print("Weighted score =", round((p / t * w), 1), "/", w)
    print()
    return round((p / t * w), 1)

def d_homework(): # This function is to make it possible to calculate the homework score.
    print("Daily homework:")
    w = int(input("Weight (0-100)? "))
    p = int(input("Total points earned? "))
    t = int(input("Total points possible? "))
    print("Total points =", p, "/", t)
    print("Weighted score =", round((p / t * w), 1), "/", w)
    print()
    return round((p / t * w), 1)


def grade(a,b,c,d,e,f): #This function is to make it possible to calculate the overall grade.
    total=a+b+c+d+e+f
    return total

def end(a,b,c,d,e,f): # This function is to print the grade and the custom massage.
    total=grade(a,b,c,d,e,f)
    print("Overall percentage =",total)
    if total >= 90:
        print("Your grade will be at least: A")
        print("<< Excellent!You are great! >>")
    elif total >= 80:
        print("Your grade will be at least: B")
        print("<< Good job! >>")
    elif total >= 70:
        print("Your grade will be at least: C")
        print("<< Good work,Go ahead! >>")
    elif total >= 60:
        print("Your grade will be at least: D")
        print("<< Lucky guy! You are passed >>")
    else:
        print("Your grade will be at least: F")
        print("<<Oops! Please work harder! >>")
    print()

def main(): # This is the main function which call the small function and without print statment.
    intro()
    a=mid_term(1)
    b=mid_term(2)
    c=final()
    d=homework()
    e=quizzes()
    f=d_homework()
    end(a,b,c,d,e,f)

main()
