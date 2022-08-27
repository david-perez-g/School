if __name__=="__main__":
    ZeroDivError = 12 / 0 #ZeroDivisionError

    list = ["Hi",12]  #IndexError
    print(list[2])

    another_list = []
    print(another_list[0])


    def Type(number): #TypeError
       result = number + 1

    Type("a")

    def Type2(text):
        for i in range(1,text):
            print(i)

    Type2("Yes")