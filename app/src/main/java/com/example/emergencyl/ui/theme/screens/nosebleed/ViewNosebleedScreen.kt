package com.example.emergencyl.ui.theme.screens.nosebleed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.emergencyl.models.anklesprain
import com.example.wazitoecommerce.data.AnkleViewModel

@Composable
fun ViewNosebleedScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var InstructionRepository = AnkleViewModel(navController, context)


        val emptyInstructionState = remember { mutableStateOf(anklesprain("","","","","")) }
        var emptyInstructionsListState = remember { mutableStateListOf<anklesprain>() }

        var instructions = InstructionRepository.allInstructions(emptyInstructionState, emptyInstructionsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Instructions",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(instructions){
                    instructions(
                        instruction = it.instruction,
                        instruction1 = it.instruction1,
                        instruction2 = it.instruction2,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        navController = navController,
                        InstructionRepository = InstructionRepository
                    )
                }
            }
        }
    }
}


@Composable
fun instructions(instruction:String,
                 instruction1:String,
                 instruction2:String,
                 imageUrl:String,
                 id: String,
                 navController: NavHostController,
                 InstructionRepository: AnkleViewModel,
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = instruction)
        Text(text = instruction1)
        Text(text = instruction2)
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )
        Button(onClick = {
            InstructionRepository.deleteInstruction(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            //navController.navigate(ROUTE_UPDATE_PRODUCTS+"/$id")
        }) {
            Text(text = "Update")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ViewFaintScreenPreview(){

    ViewNosebleedScreen(navController = rememberNavController())
}