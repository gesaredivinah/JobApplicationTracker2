package com.Gesare.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.lang.reflect.Modifier

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                    label = { Text("Applications") },
                    selected = false,
                    onClick = {
                        navController.navigate("applications")
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Add, contentDescription = null) },
                    label = { Text("Add") },
                    selected = false,
                    onClick = {
                        navController.navigate("add")
                    }
                )
            }
        }
    ) {pad->
        Column(modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(pad)) {
            NavHost(navController, startDestination = "applications") {
                composable("applications") { JobApplicationsScreen(navController) }
                composable("add") { AddJobApplicationScreen(navController) }
            }

        }

    }
}
@Composable
fun JobApplicationsScreen(navController: NavController) {
    val viewModel: JobApplicationViewModel = viewModel()
    val jobApplications by viewModel.allJobApplications.observeAsState(listOf())

    LazyColumn {
        items(jobApplications) { application ->
            Card(modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Column {


                    Text(text = "Name :            ${application.applicantName}")
                    Text(text = "Company name:     ${application.companyName}")
                    Text(text = " Interview date:  ${application.interviewDate}")
                    Text(text = " Follow-up date:  ${application.followUpDate}")

                }


            }

        }
    }
}
@Composable
fun AddJobApplicationScreen(navController: NavController) {
    val viewModel: JobApplicationViewModel = viewModel()
    var companyName by remember { mutableStateOf("") }
    var applicantName by remember { mutableStateOf("") }
    var interviewDate by remember { mutableStateOf("") }
    var followUpDate by remember { mutableStateOf("") }

    Column {
        TextField(
            value = companyName,
            onValueChange = { companyName = it },
            label = { Text("Company Name") }
        )
        TextField(
            value = applicantName,
            onValueChange = { applicantName = it },
            label = { Text("Applicant Name") }
        )
        TextField(
            value = interviewDate,
            onValueChange = { interviewDate = it },
            label = { Text("Interview Date") }
        )
        TextField(
            value = followUpDate,
            onValueChange = { followUpDate = it },
            label = { Text("Follow-Up Date") }
        )
        Button(onClick = {
            viewModel.insert(
                JobApplication(
                    companyName = companyName,
                    applicantName = applicantName,
                    interviewDate = interviewDate,
                    followUpDate = followUpDate
                )
            )
            navController.navigate("applications")
        }) {
            Text("Add Application")
        }
    }
}
