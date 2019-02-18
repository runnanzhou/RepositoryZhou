# Name of file: pokemon.py
# Name of author: Runnan Zhou
# Purpose of the program: #This function is designed to read the inputfile and
# build 2 level dictionary which is consist of the type of pokemon
# and the property of the pokemon. Then, with the input query, find the
# highest average of pokemon property and show the result to the user.
# Besides, if the result is same, then output will be the letter sequence.
# Course no., section no. semester: csc120 1c 2018summer

def main():
    '''
    This function is a summarize function which is consist of calling
    of other functions, which is a clear vision of the function i have
    used in this project.
    :return:
    '''

    poke_info, poke_type = open_file()
    p_dic1 = build_dic(poke_info, poke_type)

    p_average = average_function(p_dic1)
    search_show(p_average, poke_info)


def search_show(p_average, poke_info):
    ''' this is the function which to search the type of property of pokemon
    and find the highest average and show the type of the pokemon. i have
    changed the name of the type for the reason that will be equal to the
    input value.

    :param p_average: the average number which build in a dictionary.
    :param poke_info: the information of the property of pokemon.
    :return:
    '''
    list_poke = poke_info[0][4:11]
    qur = input()

    while qur != '':
        index = -1
        for i in range(0, len(list_poke)):
            if list_poke[i] == "sp. atk":
                list_poke[i] = "specialattack"
            elif list_poke[i] == "sp. def":
                list_poke[i] = "specialdefense"

            if qur.lower() == list_poke[i].lower():
                # this is to judge whether the input equal to the type.

                index = i

        if index != -1:
            max_poke = []
            max_number = [0]
            for key2 in p_average:
                value = p_average[key2][index]
                if max_number[0] < value:
                    # this is to preserve the highest average number.
                    max_number = [value]
                    max_poke = [key2]
                elif max_number[0] == value:
                    # this is to keep the same highest average number.
                    max_number.append(value)
                    max_poke.append(key2)

            for i in range(len(max_poke)):
                max_poke.sort()
                # this is to sort the list in a letter sequence
                print("{}: {}".format(max_poke[i].capitalize(), max_number[i]))
                # this is to print the type and number with capitalize
        qur = input()


def average_function(p_dic1):
    '''
    this function is to build a list which content is the average number
    of the property of pokemon, which use the information given by
    the dictionary.
    :param p_dic1: the first level of dictionary of pokemon's property
    :return: p_average: the average number which build in a dictionary.
    '''
    p_average = {}
    for key1 in p_dic1:
        total_p = [0, 0, 0, 0, 0, 0, 0]
        # this is to creat a list which will be filled with property
        poke_data = p_dic1[key1]

        for name in poke_data:
            data_list = poke_data[name]
            for i in range(len(data_list)):
                total_p[i] += int(data_list[i])
                # this is to add up to all the number of property.

        for i in range(len(total_p)):
            total_p[i] = int(total_p[i]) / len(poke_data)
            # this is to compute the average number of property.

        p_average[key1] = total_p
    return p_average


def build_dic(poke_info, poke_type):
    p_dic1 = {}
    for word in poke_type:
        p_dic1[word] = []
        p_dic2 = {}
        for i in range(len(poke_info)):
            if poke_info[i][2] == word:
                p_dic2[poke_info[i][1]] = poke_info[i][4:11]
                # this is to creat the level 2 of dictionary
                p_dic1[word] = (p_dic2)
                # this is to creat the level 1 of dictionary
    return p_dic1


def open_file():
    '''
    This function is to open the file and organize the information into
    a list which will be used to build a dictionary. the list is  information
    of the pokemon and the information of type of pokemon.
    :return:poke_info: the information of the property of pokemon.
            poke_type: the information of the type of pokemon.
    '''
    file_name = input()
    file = open(file_name)
    poke_info = []
    for line in file:
        poke_info.append(list(line.lower().strip().split(",")))
        # this is to append the every piece of information of property of poke
        # mon.
    poke_type = []
    for i in range(1, len(poke_info)):
        if poke_info[i][2] not in poke_type:
            poke_type.append(poke_info[i][2].lower())
            # this is to make a list of pokemon's type.
    return poke_info, poke_type


main()
