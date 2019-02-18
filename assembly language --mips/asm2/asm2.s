.data
prev:
	.word 1
beforeThat:
	.word 1
n:
	.word 2
cur:
	.word 1


row:
	.word 0
lr:
	.word 1
head:
	.word 0
tail:
	.word 0
mid:
	.word 1	
i:
	.word 1
count:
	.word 0

ascending:
	.word 0
descending:
	.word 0




runCheckString:
	.asciiz "Run Check: "
ascString:
	.asciiz "ASCENDING"
desString:
	.asciiz "DESCENDING"
neiString:
	.asciiz "NEITHER"
fibString:
	.asciiz "Fibonacci Numbers:"
lineChange:
	.asciiz "\n"
zeroOne:
	.asciiz " 0: 1"
oneOne :
	.asciiz " 1: 1"
wordCountString:
	.asciiz"Word Count: "
semicolon:
	.asciiz ": "
space:
	.asciiz " "


swap:
	.asciiz "String successfully swapped!"
.text
.globl studentMain
studentMain:
	addiu $sp, $sp, -24 # allocate stack space -- default of 24 here
	sw $fp, 0($sp) # save caller’s frame pointer
	sw $ra, 4($sp) # save return address
	addiu $fp, $sp, 20 # setup main’s frame pointer
	
	la $t0,fib
	lw $t0,0($t0)
	
L1:	beq $t0,$zero,L2
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, fibString		# $a0 = "fibString"
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa		#print '\n'
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, zeroOne		# $a0 = "zeroOne"
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa		#print '\n'
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, oneOne		# $a0 = "oneOne"
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa		#print '\n'
	syscall
	
	la $t1, prev
	lw $t1,0($t1)
	
	la $t2, beforeThat
	lw $t2,0($t2)
	
	la $t3, n
	lw $t3,0($t3)
	
	la $t4,cur
	lw $t4,0($t4)
	
	addi $t1,$zero,1
	
	addi $t2, $zero,1
	
	addi $t3,$zero,2

L1_1:	slt $s0, $t0,$t3
	bne $s0,$zero,L1_2
	
	add $t4,$t1,$t2
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, space		# $a0 = "space"
	syscall
	
	addi	$v0, $zero, 1		# print_int()
	add	$a0, $zero, $t3		# $a0 = 0 + n
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, semicolon		# $a0 = "semicolon"
	syscall
	
	addi	$v0, $zero, 1		# print_int()
	add	$a0, $zero, $t4		# $a0 = 0 + cur
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	
	addi $t3,$t3,1
	
	add $t2,$zero,$t1
	add $t1,$zero,$t4
	j L1_1
L1_2:	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa		#print '\n'
	syscall
	
	
L2:	la $t5,	square
	lw $t5, 0($t5)
	
	beq $t5,$zero,L3
	
	la $t6,square_fill
	lb $t6,0($t6)
	
	la $t7,square_size
	lw $t7,0($t7)
	
	la $t8,row
	lw $t8,0($t8)
	
	
	
Loop1:	slt $s0,$t8,$t7		#Loop iterator=row
	beq $s0,$zero,L2_4
	
	la $t1,lr
	lw $s1,0($t1)
	la $t2,mid
	lw $s2,0($t2)
	
	subi	$t9,$t7,1
	
	
	beq $t8,$zero,L2_1	#ifelse branch
	beq $t8,$t9,L2_1
	
	addi $s1,$zero,'|'
	sw $s1, 0($t1)
	add $s2,$zero,$t6
	sw $s2,0($t2)
	j L2_2
L2_1:	
	
	addi $s1,$zero,'+'
	sw $s1,0($t1)
	
	addi $s2,$zero,'_'
	sw $s2,0($t2)
	
L2_2:	addi	$v0, $zero, 4		# print_int()
	la	$a0, 0($t1)		# $a0 = 0 + lr
	syscall
	
	la $s3,i
	lw $s3,0($s3)
	
Loop2:	slt $s4,$s3,$t9		#Loop iterater=i
	beq $s4,$zero,L2_3
	
	addi	$v0, $zero, 4		# print_int()
	la	$a0, 0( $t2)		# $a0 = 0 + mid
	syscall
	
	addi $s3,$s3,1
	j Loop2
	
L2_3:	addi	$v0, $zero, 4		# print_int()
	la	$a0,  0($t1)	# $a0 = 0 + lr
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	
	addi $t8,$t8,1
	j Loop1
	
L2_4:		
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	
L3:
	la $s4,runCheck
	lw $s4,0($s4)
	beq $s4,$zero,L4
	
	la $s6,intArray_len
	lw $s6 0($s6)
	subi $t4,$s6,1
	la $s5,intArray 
	
	la $s1,descending
	lw $s1,0($s1)
	la $s2,ascending
	lw $s2,0($s2)
	addi $s3,$zero,0
	
	
	
Loop3:	slt $s0,$s3,$t4		#Loop iterator<intArray_len-1 
	beq $s0,$zero,L3_4
	
	add $s7,$s3,$s3
	add $s7,$s7,$s7
	addi $t1,$s7,4
	
	add $t7,$s5,$s7
	add $t8,$s5,$t1
	lw $t5,0($t7)
	lw $t6,0($t8) 
	
L3_1:   slt $t2,$t5,$t6 	#
	beq $t2,$zero,L3_2
	add $s2,$s2,1
L3_2:	slt $t2,$6,$t5
	beq $t2,$zero,L3_3
	add $s1,$s1,1
	
L3_3:	addi $s3,$s3,1
	j Loop3
	
L3_4:	
	beq $s1,$zero,L3_5
	beq $s2,$zero,L3_6
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, runCheckString		# $a0 = "runCheckString"
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, neiString		# $a0 = "neiString"
	syscall	
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa		#print '\n'
	syscall

L3_5:   
	addi	$v0, $zero,4		# print_str()
	la	$a0, runCheckString		# $a0 = "runCheckString"
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, desString		# $a0 = "desString"
	syscall	
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa		#print '\n'
	syscall
	bne $s2,$zero,L4
	
	
L3_6:   addi	$v0, $zero,4		# print_str()
	la	$a0, runCheckString		# $a0 = "runCheckString"
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, ascString		# $a0 = "ascString"
	syscall	
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa		#print '\n'
	syscall
	syscall
	
L4:	
	la $t5,countWords
	lw $t5,0($t5)
	beq $t5,$zero,L5
	la $t1,str
	li $t0,0
	li $t3,0
	la $t2,count
	lw $t2,0($t2)
	addi $s5,$zero,' '
Loop4_1:add $s1,$t1,$t3
	lb $s2,0($s1)
	beq $s2,$s5,Loop4_2
	j Loop4
Loop4_2:addi $t3,$t3,1
	j Loop4_1
	
Loop4:	
	add $s1,$t1,$t3
	lb $s2,0($s1)
	
	beq $s2,$zero,L4_4	#print the message when the loop is gone
	
	
	addi $s3,$s1,1
	lb $s4,0($s3)		#i+1 byte
	
	bne $s2,$s5,L4_3
	beq $s4,$s5,L4_3
	addi $t2,$t2,1
L4_3: 	addi $t3,$t3,1
	j Loop4
L4_4:
	addi	$v0, $zero,4		# print_str()
	la	$a0, wordCountString	# $a0 = "wordCountString"
	syscall	
	
	addi	$v0, $zero,1		# print_int()
	add	$a0, $zero,$t2		# $a0 = "count"
	syscall	

	addi	$v0, $zero,11
	addi	$a0, $zero,0xa		#print '\n'
	syscall
	syscall



L5:
	la $t0,revString
	lw $t0,0($t0)
	beq $t0,$zero,Done
	la $t1,str
	la $t2,head
	lw $t3,0($t2)
	la $t4,tail
	lw $t5,0($t4)
Loop5:	add $s1, $t5,$t1
	add $s4,$t3,$t1
	lb $t6,0($s1)
	beq $t6,$zero,L5_2
	addi,$t5,$t5,1
	
	j Loop5
L5_2:	subi $t5,$t5,1
	add $s1, $t5,$t1
	lb $t6,0($s1)
Loop5_1:slt $s2,$t3,$t5
	beq $s2,$zero,L5_3
	add $s3, $t3,$zero
	add $t3,$zero,$t5
	sw $t3,0($s4)
	add $t5,$zero,$s3
	sw $t5,0($s1)
	addi $t3,$t3,1
	subi $t5,$t5,1
	j Loop5_1
	
L5_3:	addi	$v0, $zero,4		# print_str()
	la	$a0, swap		
	syscall	
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa		#print '\n'
	syscall
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa		#print '\n'
	syscall





Done:
	lw $ra, 4($sp) # get return address from stack
	lw $fp, 0($sp) # restore the caller’s frame pointer
	addiu $sp, $sp, 24 # restore the caller’s stack pointer
	jr $ra # return to caller’s code

