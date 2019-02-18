def intro():
    print("This program reports information about DNA")
    print("nucleotide sequences that may encode proteins.")

def region_name(i,words,region_list):
    if i % 2 ==0:
        region_list.append(words)
    return region_list

def nucleotides(i,words,nuc_list):
    if i%2==1:
        nuc_list.append(words)
    return nuc_list




def main():
    intro()
    file_name=input("Input file name? ")
    output_file=input("Output file name? ")
    file=open(file_name)
    line=file.readlines()
    region_list=[]*10
    nuc_list=[]*10
    halfline = len(line) // 2 + 1



    for i in range(0, len(line)):
        words=line[i].strip()
        region_list=region_name(i,line,region_list)
        nuc_list=nucleotides(i,line,nuc_list)
    for n in range(0,halfline):
        print(region_list[n])
        print(nuc_list[n])

main()
def nuc_counts(i,line):
    list=[]
    halfline=len(line)//2+1
    count_A=0
    count_C=0
    count_G=0
    count_T=0
    if i % 2 != 0:
        for letter in line[i]:
            if letter == "a":
                count_A += 1
            elif letter == 'c':
                count_C += 1
            elif letter == 'g':
                count_G += 1
            elif letter == 't':
                count_T += 1
            list[(i-1)//2].append(count_A)
            list[(i - 1) // 2].append(count_C)
            list[(i - 1) // 2].append(count_G)
            list[(i - 1) // 2].append(count_T)
            print(list)

        print("Nuc. Counts:",list)