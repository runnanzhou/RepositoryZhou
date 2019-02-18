def main():
    file = open("ratings-small.txt")
    lines = file.readlines()
    book_set = set()
    book_dic = {}
    book_list=[]
    judge = input("next task? ")
    while judge != "quit":
        if judge == "averages":
            average(data_book, dic)
        elif judge == "recommend":
            user = input("user? ")
            if user not in dic:
                average(data_book, dic)
            else:
                recommend(dic, data_book, user)
        print("")
        judge = input("next task? ")

    for line in lines:
        line=line.strip()
    for i in range((len(lines)) // 3):
        lines[i] = lines[i].strip()
        book_name = lines[1 + 3 * i].strip()
        book_set.add(book_name)
        book_dic[lines[3 * i].strip()] = []
        book_list = list(book_set)


    for key in book_dic:
        value = [0] * len(book_list)
        for n in range(((len(lines)+1) // 3)):
            if lines[3*n].strip()==key:
                book_index = book_list.index(lines[1 + 3 * n].strip())
                value[book_index] = lines[2 + 3 * n].strip()
                book_dic[key] = value


    print("Welcome to the CSC110 Book Recommender. Type the word in the")
    print("left column to do the action on the right.")
    print("recommend : recommend books for a particular user")
    print("averages  : output the average ratings of all books in the system")
    print("quit      : exit the program")
    print("next task?")







    list_average=[]
    for n in range(0,len(book_list)):
        num = 0
        book_average=0
        for key in book_dic:

            book_average+=float(book_dic[key][n])


            if book_dic[key][n]!=0:
                num+=1

        book_average=book_average/num
        list_average.append((book_average,book_list[n]))
        list_average.sort(reverse=True)



    user_name=input("user? ")
    list_user=[]
    if user_name not in book_dic:
        print(list_average)
    else:
        for key in book_dic:
            simi=0
            for i in range(len(book_list)):
                simi+=int(book_dic[user_name][i])*int(book_dic[key][i])

            list_user.append((simi,key))
        list_user.sort(reverse=True)
        list_user.pop(0)

        list_rating=[]
        for n in range(len(book_list)):
            average_rating=0
            count=0
            for i in range(0,3):
                average_rating+=float(book_dic[list_user[i][1]][n])
                if book_dic[list_user[i][1]][n]!=0:
                    count+=1
            average_rating=average_rating/count
            list_rating.append((average_rating,book_list[n]))
            list_rating.sort(reverse=True)
        print(list_rating)









main()
