# Name: Runnan Zhou 18 Spring CSC110
# Section: 1N
# due day: 04/25/18
# purpose: To build a program which is able to compose a solo randomly and print chord progression
# based on the user input key and number.
import random

NUM_NOTE = 24
NUM_CHORD=6


def main():
    '''
    This function is the summary of the overall function. It is a concise function without any
    print statement.
    :return:
    '''
    dic_chords = open_dic("chords.txt")
    dic_notes = open_dic("notes.txt")
    key1, num_chords = Input()
    chord_pro(dic_chords, key1, num_chords)
    solo_list_cho = random_num(dic_notes, key1)
    solo_list_create(solo_list_cho)


def Input():
    '''
    This function is to prompt to the user input. The input define the location of the dictionary
    and the number of chords.
    :return: key1, num_chords
    '''
    print("Hello! Welcome!")
    key1 = input("What key would you like to play in? ")
    num_chords = int(input("How many unique chords? (up to 6) "))
    return key1, num_chords


def chord_pro(dic_chords, key1, num_chords):
    '''
    This function is to make the chord progression which is composed of 4 random selected chord.
    :param dic_chords: the dictionary of chords.txt
    :param key1: the user input define the location of key
    :param num_chords: the user
    :return:
    '''
    chords_pro = set()
    while len(chords_pro) < num_chords:
        rand_num = random.randint(0, len(dic_chords[key1]) - 1)
        chords_pro.add(dic_chords[key1][rand_num])
    print("Chord Progression: ", end='')
    for letter in chords_pro:
        print(letter, end=' ')
    print()


def random_num(dic_notes, key1):
    '''
    This function is to make the list of solo_list.Choose the chord randomly.
    :param dic_notes: the dictionary of chords.txt
    :param key1: the user input define the location of ke
    :return:solo_list_cho
    '''
    solo_list_cho = []
    while len(solo_list_cho) < NUM_NOTE:
        rand_num = random.randint(0, len(dic_notes[key1]) - 1)
        solo_list_cho.append(dic_notes[key1][rand_num])
    return solo_list_cho


def solo_list_create(solo_list_cho):
    '''
    This function is to create the list of list and dispatch the chord to different head.
    :param solo_list_cho: the list of choosing from the note.txt
    :return:
    '''
    solo_list = []
    solo_key = ["e", "B", "G", "D", "A", "E"]
    for x in range(NUM_CHORD):
        solo_inner = []
        solo_list.append(solo_inner)
        solo_inner.append(solo_key[x])
        solo_inner.append("|")
    # juge each element to append the right character.
    for a in solo_list_cho:
        for i in range(0, len(solo_key)):
            if a[0] == solo_key[i]:
                if len(a) == 2:
                    solo_list[i].append(a[1:] + "—-")
                elif len(a) == 3:
                    solo_list[i].append(a[1:] + "-")
            elif a[0] != solo_key[i]:
                solo_list[i].append("—--")
    for j in range(len(solo_list)):
        solo_list[j].insert(len(solo_list[j]), '\n')
        for k in range(0, len(solo_list[j])):
            print(solo_list[j][k], end="")


def open_dic(file_name):
    '''
    This function is to open the file and read the lines and make a dictionary.
    :param file_name: the name of the file.
    :return: diction
    '''
    diction = {}
    file = open(file_name)
    lines = file.readlines()
    for line in lines:
        line = line.split()
        diction[line[0]] = line[1:]
    return diction


main()
