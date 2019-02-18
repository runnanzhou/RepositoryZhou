.data 
single:
	.word 1
mediantext:
	.asciiz "median"
comparisons:
	.asciiz "Comparisons"
out:
	.asciiz ": "
onetext:
	.asciiz "one"
twotext:
	.asciiz "two"
anotherone:
	.asciiz "'one'"
anothertwo:
	.asciiz "'two'"
anotherthree:
	.asciiz"'three'"
threetext:
	.asciiz "three"
negative:
	.asciiz " was negative"
	
sumtext:
	.asciiz "sum: "
space: 
	.asciiz " "
	

.text
.globl studentMain
studentMain:
	addiu $sp, $sp, -24 # allocate stack space -- default of 24 here
	sw $fp, 0($sp) # save caller’s frame pointer
	sw $ra, 4($sp) # save return address
	addiu $fp, $sp, 20 # setup main’s frame pointer

	la $t0,median #load the word
	lw $t0,0($t0)
	
	la $t1, single #load the word
	lw $t1,0($t1)
	la $t6, one  #load the word set the address at the $t6
	lw $t2,0($t6)
	
	la $t7, two  #load the word set the address at the $t7
	lw $t3,0($t7)
	
	la $t8, three  #load the word set the address at the $t8
	lw $t4,0($t8)
	
	la $t5,absVal #load the word
	lw $t5,0($t5)
	
	la $t9, sum 
	lw $t9, 0($t9)
	
	la $a3,rotate
	lw $a3,0($a3)
	bne $t0, $t1, L2 #branch, if median !=1 then go to L2,
	
	
	
L1:	bne $t2, $t3, L1_2 # branch, if one != two, then go to L1_2
	addi	$v0, $zero, 4		# print_str()
	la	$a0, mediantext		
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, out		# $a0 = ": "
	syscall
	
	addi	$v0, $zero,1		# print_int()
	lw	$a0, 0($t2)		
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	
	j L1_7
	
L1_2:	bne $t2,$t4,L1_3 # branch, if one == three, then go to L2
	addi	$v0, $zero, 4		# print_str()
	la	$a0, mediantext		
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, out		# $a0 = ": "
	syscall
	
	addi	$v0, $zero,1		# print_int()
	lw	$a0, 0($t2)		
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	
	j L1_7

	
	
	
	
	
	
	
	
L1_3:	bne $t3,$t4,L1_4  # branch, if two != three, then go to L1_4
	addi	$v0, $zero, 4		# print_str()
	la	$a0, mediantext		
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, out		# $a0 = ": "
	syscall
	
	addi	$v0, $zero,1		# print_int()
	lw	$a0, 0($t3)		
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	
	
	
	
	
	
	
	
	
L1_4:	
	slt $s1, $t2,$t3 
	slt $s2, $t2,$t4
	slt $s3, $t3,$t4
	
	addi	$v0, $zero, 4		# print_str()
	la	$a0, comparisons		
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, out		# $a0 = ": "
	syscall
	
	addi	$v0, $zero,1		# print_int()
	add	$a0, $zero,$s1		
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, space		# $a0 = ": "
	syscall
	
	addi	$v0, $zero,1		# print_int()
	add	$a0, $zero,$s2			
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, space		# $a0 = ": "
	syscall
	
	addi	$v0, $zero,1		# print_int()
	add	$a0, $zero,$s3			
	syscall
	
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	
	
	
	bne $s1, $s3, L1_5
	addi	$v0, $zero, 4		# print_str()
	la	$a0, mediantext		
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, out		# $a0 = ": "
	syscall
	
	addi	$v0, $zero,1		# print_int()
	lw	$a0, 0($t3)		
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	j L4
		
	
L1_5:	
	beq $s1, $s2, L1_6
	addi	$v0, $zero, 4		# print_str()
	la	$a0, mediantext		
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, out		# $a0 = ": "
	syscall
	
	addi	$v0, $zero,1		# print_int()
	add	$a0, $zero,$t2			
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	
L1_6:	
	beq $s2, $s3, L1_7
	addi	$v0, $zero, 4		# print_str()
	la	$a0, mediantext		
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, out		# $a0 = ": "
	syscall
	
	addi	$v0, $zero,1		# print_int()
	add	$a0, $zero,$t4		
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall

	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	
		

L1_7:
	addi	$v0, $zero,11
	addi	$a0, $zero,0xa
	syscall
	


L2:
		
	la $t1, single #load the word
	lw $t1,0($t1)
	bne $t5, $t1, L3
	
	slt $s4, $t2,$zero 
	slt $s5, $t3,$zero
	slt $s6, $t4,$zero
	beq $s4,$zero,L2_1
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, anotherone		
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, negative		
	syscall
	
	addi	$v0, $zero,11		#print \n
	addi	$a0, $zero,0xa
	syscall
	
	sub $t2, $zero,$t2
	la $s1, one 
	sw $t2,0($s1)
			
	
	
	
L2_1:
	beq $s5,$zero,L2_2
	addi	$v0, $zero,4		# print_str()
	la	$a0, anothertwo		
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, negative		
	syscall
	
	addi	$v0, $zero,11		#print \n
	addi	$a0, $zero,0xa
	syscall	
	
	sub $t3, $zero,$t3
	la $s2, two 
	sw $t3,0($s2)
	
L2_2:
	beq $s6,$zero,L2_3
	addi	$v0, $zero,4		# print_str()
	la	$a0, anotherthree
	syscall
	
	addi	$v0, $zero,4		# print_str()
	la	$a0, negative		
	syscall
	
	addi	$v0, $zero,11		#print \n
	addi	$a0, $zero,0xa
	syscall	
	
	sub $t4, $zero,$t4
	la $s3, three 
	sw $t4,0($s3)
	
	
L2_3:
	addi	$v0, $zero,11		#print \n
	addi	$a0, $zero,0xa
	syscall	


L3:
	
	la $t1, single #load the word
	lw $t1,0($t1)
	bne $t9, $t1, L4
	
	add $t9,$t2,$t3
	add $t9,$t9,$t4 		#compute the sum of one , two, three
	
	addi	$v0, $zero, 4		# print_str()
	la	$a0, sumtext		
	syscall
	
	addi	$v0, $zero,1		# print_int()
	add	$a0,$zero, $t9		
	syscall
	
	addi	$v0, $zero,11		#print \n
	addi	$a0, $zero,0xa
	syscall
	syscall

L4:
	la $t1, single #load the word
	lw $t1,0($t1)
	bne $a3, $t1, L5
	
	add $t6, $zero, $t2
	add $t7, $zero, $t3
	add $t8, $zero, $t4
	
	la $s1, one 
	sw $t8,0($s1)
	la $t2,one
	lw $t2,0($t2)
	la $s2, two 
	sw $t6,0($s2)
	la $t3,two
	lw $t3,0($t3)
	la $s3, three 
	sw $t7,0($s3)
	la $t4,three
	lw $t4,0($t4)

L5:
	la $a1, dump
	lw $a1, 0($a1)
	la $t1, single #load the word
	lw $t1,0($t1)
	bne $a1, $t1, DONE
	addi	$v0, $zero, 4		# print_str()
	la	$a0, onetext		
	syscall
	addi	$v0, $zero,4		# print_str()
	la	$a0, out		# $a0 = ": "
	syscall
	addi	$v0, $zero,1		# print_int()
	add	$a0,$zero, $t2		
	syscall
	addi	$v0, $zero,11		#print \n
	addi	$a0, $zero,0xa
	syscall
	
	
	addi	$v0, $zero, 4		# print_str()
	la	$a0, twotext		
	syscall
	addi	$v0, $zero,4		# print_str()
	la	$a0, out		# $a0 = ": "
	syscall
	addi	$v0, $zero,1		# print_int()
	add	$a0,$zero, $t3		
	syscall
	addi	$v0, $zero,11		#print \n
	addi	$a0, $zero,0xa
	syscall
		
	addi	$v0, $zero, 4		# print_str()
	la	$a0, threetext		
	syscall
	addi	$v0, $zero,4		# print_str()
	la	$a0, out		# $a0 = ": "
	syscall
	addi	$v0, $zero,1		# print_int()
	add	$a0,$zero, $t4		
	syscall
	addi	$v0, $zero,11		#print \n
	addi	$a0, $zero,0xa
	syscall
	addi	$v0, $zero,11		#print \n
	addi	$a0, $zero,0xa
	syscall
	
	
	
	
	

DONE:
	
	lw $ra, 4($sp) # get return address from stack
	lw $fp, 0($sp) # restore the caller’s frame pointer
	addiu $sp, $sp, 24 # restore the caller’s stack pointer
	jr $ra # return to caller’s code
