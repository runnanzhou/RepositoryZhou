# Runnan Zhou, Student, CSc 110, spring 2018, Section n
# Programming Assignment #6, 03/7/2018
# This function is to make it available for user to guess the number which is random produced by computer.And i use
#while loop to make it possible to guess several times. And i use return value to take the number out of function.
import random
def intro(): # The function is to show the user the introduction part of this function.
    print("The show on the shoe")
    print("I have already know")
    print("Let us go")
    print()
def guessing(h): # This function is to play the game once.
    print("I'm thinking of a number between 1 and",str(h)+"...")
    random_number=random.randint(1,h) # Produce a random number by computer.
    guess_input=int(input("Your guess? "))
    count=1
    while guess_input!=random_number : # To make it possible to guess the number several times and remind the user
# whether it is lower or higher.
        if guess_input < random_number :
            print("It's higher.")
        elif guess_input > random_number:
            print("It's lower.")
        guess_input=int(input("Your guess? "))
        count+=1 # To calculate how many guesses did the user do.
    if guess_input==random_number and count!=1:
# To judge whether the user guess the number in the first time.
        print("You got it right in",count,"guesses!")
    elif guess_input==random_number and count==1:
        print("You got it right in 1 guess!")
    return count

def statistics(total_guesses,count,best_game): # This function is to make a statistics report to show the detail
# information which include the total guesses and total game the user did.
    print("Overall results:")
    print("Total games   =", count)
    print("Total guesses =", total_guesses)
    print("Guesses/game  =", round((total_guesses / count), 1))
    print("Best game     =", best_game)

def main(h):
# This is the main function which to call the play function mutiple times and call other important function
    intro()
    g=guessing(h)
    total_guesses=0
    best_game=100
    count=1
    n=input("Do you want to play again? ").lower()
    best_game=min(best_game,g)
    while n[0]=="y":
# Call the function multiple time when it is "y" in the first letter.
       d=guessing(h)
       total_guesses+=d
       best_game=min(g,best_game,d)
# To compare the best guess number in the several plays.
       count+=1
       n=input("Do you want to play again? ")
    total_guesses=total_guesses+g
# It is the total guesses.
    statistics(total_guesses,count,best_game)
# Call the statistics report.
main(10)
